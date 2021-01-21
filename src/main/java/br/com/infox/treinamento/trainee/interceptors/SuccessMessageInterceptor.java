package br.com.infox.treinamento.trainee.interceptors;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuccessMessage
@Interceptor
public class SuccessMessageInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger("trainee.msg.interceptor");

	@AroundInvoke
	public Object interceptarMetodo(InvocationContext context) throws Exception {
		try {
			LOG.info(String.format("ANTES DE INVOCAR %s.%s", context.getMethod().getDeclaringClass().getName(), context.getMethod().getName()));
			Object proceed = context.proceed();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", null));
			return proceed;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operação falhou", null));
			throw e;
		} finally {
			LOG.info(String.format("APÓS DE INVOCAR %s.%s", context.getMethod().getDeclaringClass().getName(), context.getMethod().getName()));
		}
	}

}
