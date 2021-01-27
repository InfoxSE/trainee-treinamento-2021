package br.com.infox.treinamento.trainee.pessoafisica;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<CPF, String> {

	@Override
	public void initialize(CPF constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return CpfUtil.isValid(value);
	}

}
