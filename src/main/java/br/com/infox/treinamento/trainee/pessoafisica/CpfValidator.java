package br.com.infox.treinamento.trainee.pessoafisica;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("pessoafisica.CpfValidator")
public class CpfValidator implements Validator {
	private static final Logger LOG = Logger.getLogger("trainee.converters");
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			LOG.info("Iniciando método validate de CpfValidator");
			String cpf = (String) value;
			if (!isValid(cpf)) {
				throw new ValidatorException(new FacesMessage("CPF inválido. Dígitos verificadores inválidos"));
			}
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage("CPF inválido"), e);
		} finally {
			LOG.info("Encerrando método validate de CpfValidator");
		}
	}
	private boolean isValid(String cpf) {
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma = soma + parseInt(cpf.charAt(i)) * (10 - i);
        }
        int dv1 = 11 - (soma % 11);
        if (dv1 == 10 || dv1 == 11) {
            dv1 = 0;
        }
        if (dv1 != parseInt(cpf.charAt(9))) {
            return false;
        }
        soma = 0;
        for (int i = 0; i < 9; i++) {
            soma = soma + parseInt(cpf.charAt(i)) * (11 - i);
        }
        soma = soma + dv1 * 2;
        int dv2 = 11 - (soma % 11);
        if (dv2 == 10 || dv2 == 11) {
            dv2 = 0;
        }
        if (dv2 != parseInt(cpf.charAt(10))) {
            return false;
        }
        return true;
    }

    private int parseInt(Character c) {
        return Integer.parseInt(c.toString());
    }

}
