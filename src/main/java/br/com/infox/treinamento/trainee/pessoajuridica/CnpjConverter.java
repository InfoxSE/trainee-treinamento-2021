package br.com.infox.treinamento.trainee.pessoajuridica;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("pessoajuridica.CnpjConverter")
public class CnpjConverter implements Converter {
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()) {
			return null;
		}
		return value.replaceAll("\\D", "");
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) {
			return null;
		}
		StringBuilder cnpj = new StringBuilder((String)value);
		cnpj.insert(12, "-");
		cnpj.insert(8, "/");
		cnpj.insert(5, ".");
		cnpj.insert(2, ".");
		return cnpj.toString();
	}
}
