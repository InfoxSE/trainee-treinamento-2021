package br.com.infox.treinamento.trainee.pessoafisica;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

public interface I18nEnum {

	String MESSAGES_BUNDLE = "br.com.infox.treinamento.i18n.Messages";

	default String getLabel() {
		Locale defaultLocale = Optional.ofNullable(FacesContext.getCurrentInstance()).map(FacesContext::getApplication)
                .map(Application::getDefaultLocale).orElseGet(() -> new Locale("pt", "BR"));
		return ResourceBundle.getBundle(MESSAGES_BUNDLE, defaultLocale).getString(getI18nKey());
	}

	default String getI18nKey() {
		if (getClass().isEnum()) {
			Enum<?> thisEnum = (Enum<?>) this;
			return String.format("%s.%s", getClass().getName(), thisEnum.name());
		} else {
			Locale defaultLocale = Optional.ofNullable(FacesContext.getCurrentInstance()).map(FacesContext::getApplication)
	                .map(Application::getDefaultLocale).orElseGet(() -> new Locale("pt", "BR"));
			String message = ResourceBundle.getBundle(MESSAGES_BUNDLE, defaultLocale).getString("I18nEnum.nonEnum");
			throw new UnsupportedOperationException(message);
		}
	}

}
