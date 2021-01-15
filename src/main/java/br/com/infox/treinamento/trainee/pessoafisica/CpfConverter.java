package br.com.infox.treinamento.trainee.pessoafisica;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("pessoafisica.CpfConverter")
public class CpfConverter implements Converter {
	private static final Logger LOG = Logger.getLogger("trainee.converters");
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			LOG.info("ENTRANDO EM MÉTODO getObject do CpfConverter");
			if (value == null || value.isEmpty()) {
				return null;
			}
			return value.replaceAll("\\D", "");
		} finally {
			LOG.info("SAINDO DE MÉTODO getObject do CpfConverter");
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		try {
			LOG.info("ENTRANDO EM MÉTODO getAsString do CpfConverter");
			if (value == null) {
				return null;
			}
			StringBuilder cpf = new StringBuilder((String)value);
			cpf.insert(9, "-");
			cpf.insert(6, ".");
			cpf.insert(3, ".");
			return cpf.toString();
		} finally {
			LOG.info("SAINDO DE MÉTODO getAsString do CpfConverter");
		}
	}

}
