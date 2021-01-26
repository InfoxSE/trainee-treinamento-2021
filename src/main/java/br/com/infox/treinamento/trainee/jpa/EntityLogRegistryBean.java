package br.com.infox.treinamento.trainee.jpa;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.servlet.http.HttpServletRequest;

@Stateless
@LocalBean
public class EntityLogRegistryBean {

	@PersistenceContext(unitName = "primary")
	private EntityManager primary;
	@PersistenceContext(unitName = "primaryAudit")
	private EntityManager audit;

	public void registrarLog(TipoOperacaoLogEnum operacao, Object entidadeJpa) {
		if (entidadeJpa != null) {
			try {
				String idEntidade = resolveEntityIdValue(entidadeJpa);
				List<EntityLogDetail> entityLogDetailList = resolveEntityLogDetailList(entidadeJpa);
				EntityLog log = new EntityLog();
				log.setIp(getIpRequest());
				log.setUrlRequisicao(getUrlRequest());
				log.setOperacao(operacao);
				log.setNomeEntidade(entidadeJpa.getClass().getName());
				log.setIdEntidade(idEntidade);
				log.setEntityLogDetailList(entityLogDetailList);
				audit.persist(log);
			} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	private List<EntityLogDetail> resolveEntityLogDetailList(Object entidadeJpa)
			throws IllegalAccessException, InvocationTargetException, IntrospectionException {
		BeanInfo beanInfo = resolveBeanInfo(entidadeJpa);
		EntityType<?> entityMetaModel = resolveEntityMetaModel(entidadeJpa);
		Set<String> jpaAttributes = entityMetaModel.getAttributes().stream().map(Attribute::getName)
				.collect(Collectors.toSet());
		List<EntityLogDetail> entityLogDetailList = new ArrayList<>(0);
		for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
			final String propertyName = propertyDescriptor.getName();
			final String propertyValue = resolveValue(entidadeJpa, propertyDescriptor);
			if (jpaAttributes.contains(propertyName)) {
				EntityLogDetail e = new EntityLogDetail();
				e.setNomeAtributo(propertyName);
				e.setValor(propertyValue);
				entityLogDetailList.add(e);
			}
		}
		return entityLogDetailList;
	}

	private EntityType<?> resolveEntityMetaModel(Object entidadeJpa) {
		return primary.getMetamodel().entity(entidadeJpa.getClass());
	}

	private BeanInfo resolveBeanInfo(Object entidadeJpa) throws IntrospectionException {
		return Introspector.getBeanInfo(entidadeJpa.getClass(), Object.class);
	}

	private String resolveEntityIdValue(Object entidadeJpa)
			throws IllegalAccessException, InvocationTargetException, IntrospectionException {
		BeanInfo beanInfo = resolveBeanInfo(entidadeJpa);
		EntityType<?> entityMetaModel = resolveEntityMetaModel(entidadeJpa);
		Class<?> idJavaType = entityMetaModel.getIdType().getJavaType();
		String idPropertyName = entityMetaModel.getId(idJavaType).getName();
		String idEntidade = null;
		for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
			final String propertyName = propertyDescriptor.getName();
			final String propertyValue = resolveValue(entidadeJpa, propertyDescriptor);
			if (propertyName.equals(idPropertyName)) {
				idEntidade = propertyValue;
				break;
			}
		}
		return idEntidade;
	}

	public static EntityLogRegistryBean instance() {
		try {
			InitialContext initialContext = new InitialContext();
			EntityLogRegistryBean lookup = (EntityLogRegistryBean) initialContext
					.lookup("java:module/EntityLogRegistryBean");
			return lookup;
		} catch (NamingException e) {
			throw new IllegalStateException(e);
		}
	}

	private String resolveValue(Object entidadeJpa, PropertyDescriptor propertyDescriptor)
			throws IllegalAccessException, InvocationTargetException {
		Object idValue = propertyDescriptor.getReadMethod().invoke(entidadeJpa);
		String idEntidade = Optional.ofNullable(idValue).map(Object::toString).orElse(null);
		return idEntidade;
	}

	private static String getIpRequest() {
		HttpServletRequest request = getRequest();
		if (request == null) {
			return null;
		}
		return request.getRemoteAddr();
	}

	private static String getUrlRequest() {
		HttpServletRequest request = getRequest();
		if (request == null) {
			return null;
		}
		return getRequest().getRequestURL().toString();
	}

	private static HttpServletRequest getRequest() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc == null) {
			return null;
		}
		return (HttpServletRequest) fc.getExternalContext().getRequest();
	}

}
