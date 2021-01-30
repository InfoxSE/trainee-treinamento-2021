package br.com.infox.treinamento.trainee.pessoajuridica;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("pessoajuridica.CnpjConverter")
public class CnpjConverter implements Converter {

		private static final Logger LOG = Logger.getLogger("trainee.converters");
		
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			try {
				LOG.info("ENTRANDO NO MÉTODO getAsObject do CnpjConverter");
				if(value == null || value.isEmpty()) {
					return null;
				}
				return value.replaceAll("\\D", "");
			} finally {
				LOG.info("SAINDO DO MÉTODO getAsObject do CnpjConverter");
			}
			
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			try {
				LOG.info("ENTRANDO NO MÉTODO getAsString do CnpjConverter");
				if(value == null) {
					return null;
				}
				StringBuilder cnpj = new StringBuilder((String)value);
				cnpj.insert(12, "-");
				cnpj.insert(8, "/");
				cnpj.insert(5, ".");
				cnpj.insert(2, ".");
				return cnpj.toString();
			} finally {
				LOG.info("SAINDO DO MÉTODO getAsString do CnpjConverter");
			}
		}
}
