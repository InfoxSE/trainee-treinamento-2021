package br.com.infox.treinamento.trainee.pessoajuridica;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("pessoajuridica.CnpjValidator")
public class CnpjValidator implements Validator {
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			String cnpj = (String) value;
			if (!isValid(cnpj)) {
				throw new ValidatorException(new FacesMessage("CNPJ inválido. Dígitos verificadores inválidos"));
			}
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage("CNPJ inválido"), e);
		}
	}
	
	public boolean isValid(String cnpj) {
	    
		if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
				|| cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
				|| cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
				|| cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
				|| cnpj.equals("88888888888888") || cnpj.equals("99999999999999")) {
			return (false);
		}
			  
		char dig13, dig14;
		int sm, i, r, num, peso; 
		
		sm = 0;
		peso = 2;
		for (i = 11; i >= 0; i--) {  
			num = (int) (cnpj.charAt(i) - 48);
			sm = sm + (num * peso);
			peso = peso + 1;
			
			if (peso == 10) {
				peso = 2;
			}
		}
		
		r = sm % 11;
		if ((r == 0) || (r == 1)) {
			dig13 = '0';
		}else {
			dig13 = (char) ((11 - r) + 48);
		}
		
		sm = 0;
		peso = 2;
		for (i = 12; i >= 0; i--) {
			num = (int) (cnpj.charAt(i) - 48);
			sm = sm + (num * peso);
			peso = peso + 1;
			
			if (peso == 10) {
				peso = 2;
			}
		}
		
		r = sm % 11;
		if ((r == 0) || (r == 1)) {
			dig14 = '0';
		}else {
			dig14 = (char) ((11 - r) + 48);
		}
		
		if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) {
			return true;
		}else {
			return false;
		}
	}
}
