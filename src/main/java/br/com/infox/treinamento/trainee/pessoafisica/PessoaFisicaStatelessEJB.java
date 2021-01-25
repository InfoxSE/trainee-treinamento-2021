package br.com.infox.treinamento.trainee.pessoafisica;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.infox.treinamento.trainee.pessoafisica.cdi.PessoaFisicaEvent;
import br.com.infox.treinamento.trainee.pessoafisica.cdi.PessoaFisicaEventType;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PessoaFisicaStatelessEJB implements PessoaFisicaService {

	private static final Logger LOG = Logger.getLogger("trainee.slsb");

	private int quantidadeAcessos = 0;

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@Inject
	private Event<PessoaFisicaEvent> dispatcher;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct "+getClass().getSimpleName());
	}
	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy "+getClass().getSimpleName());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrar(PessoaFisica novaPessoa) {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A STATELESS SESSION BEAN => "+this.quantidadeAcessos);
		PessoaFisica pessoa = novaPessoa;
		PessoaFisicaEventType tipoEvento = PessoaFisicaEventType.INSERT;
		if (pessoa.getId() == null) {
			entityManager.persist(pessoa);
		} else {
			tipoEvento = PessoaFisicaEventType.UPDATE;
			pessoa = entityManager.merge(pessoa);
		}
		pessoa.setName(pessoa.getName()+" x");
		dispatcher.fire(new PessoaFisicaEvent(tipoEvento, pessoa));
	}
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(PessoaFisica pessoa) {
		pessoa = entityManager.find(PessoaFisica.class, pessoa.getId());
		entityManager.remove(pessoa);
		dispatcher.fire(new PessoaFisicaEvent(PessoaFisicaEventType.REMOVE, pessoa));
	}

	@Override
	public List<PessoaFisica> recuperarPessoas() {
		return recuperarPessoas(null, null);
	}

	@Override
	public List<PessoaFisica> recuperarPessoas(Integer offset, Integer limit) {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A STATELESS SESSION BEAN => "+this.quantidadeAcessos);

		String jpqlRecuperarPessoas = "select pessoa from PessoaFisica pessoa";
		TypedQuery<PessoaFisica> createQuery = entityManager.createQuery(jpqlRecuperarPessoas, PessoaFisica.class);
		if (offset != null) {
			createQuery.setFirstResult(offset);
		}
		if (limit != null) {
			createQuery.setMaxResults(limit);
		}
		List<PessoaFisica> resultList = createQuery.getResultList();
		return resultList;
	}

}
