package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;

@Named
@RequestScoped
@Stereotype
@Retention(RUNTIME)
@Target(TYPE)
public @interface TraineeModel {

}
