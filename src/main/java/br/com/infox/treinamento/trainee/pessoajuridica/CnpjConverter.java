package br.com.infox.treinamento.trainee.pessoajuridica;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("pessoajuridica.CnpjConverter")
public class CnpjConverter implements Converter{
	private static final Logger LOG = Logger.getLogger("trainee.converters");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			LOG.info("ENTRANDO EM MÉTODO getObject do CnpjConverter");
			if (value == null || value.isEmpty()) {
				return null;
			}
			return value.replaceAll("\\D", "");
		} finally {
			LOG.info("SAINDO DE MÉTODO getObject do CnpjConverter");
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		try {
			LOG.info("ENTRANDO EM MÉTODO getAsString do CnpjConverter");
			if (value == null) {
				return null;
			}
			StringBuilder cnpj = new StringBuilder((String)value);
			// Máscara do CNPJ "99.999.999/9999-99"
			cnpj.insert(12, "-");
			cnpj.insert(8, "/");
			cnpj.insert(5, ".");
			cnpj.insert(2, ".");
			return cnpj.toString();
		} finally {
			LOG.info("SAINDO DE MÉTODO getAsString do CnpjConverter");
		}
	}
	
	
	

}
