package br.com.infox.treinamento.trainee.pessoafisica;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("pessoafisica.CpfValidator")
public class CpfValidatorJsf implements Validator {
	private static final Logger LOG = Logger.getLogger("trainee.converters");
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			LOG.info("Iniciando método validate de CpfValidator");
			String cpf = (String) value;
			if (!CpfUtil.isValid(cpf)) {
				throw new ValidatorException(new FacesMessage("CPF inválido. Dígitos verificadores inválidos"));
			}
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage("CPF inválido"), e);
		} finally {
			LOG.info("Encerrando método validate de CpfValidator");
		}
	}

}
