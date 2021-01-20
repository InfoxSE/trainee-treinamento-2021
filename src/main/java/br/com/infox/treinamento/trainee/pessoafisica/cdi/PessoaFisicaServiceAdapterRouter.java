package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisicaServiceAdapter;

@ApplicationScoped
public class PessoaFisicaServiceAdapterRouter implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean usaAdaptadorDadosSensiveis = false;
	@Named("fiboAnterior")
	@Produces
	@FibonacciAnterior
	private int anterior = 0;
	@Named("fiboAtual")
	@Produces
	@FibonacciAtual
	private int atual = 1;

	public boolean isUsaAdaptadorDadosSensiveis() {
		return usaAdaptadorDadosSensiveis;
	}

	public void setUsaAdaptadorDadosSensiveis(boolean usaAdaptadorDadosSensiveis) {
		this.usaAdaptadorDadosSensiveis = usaAdaptadorDadosSensiveis;
	}

	@Produces
	@Router
	@Dependent
	public PessoaFisicaServiceAdapter escolherDadosSensiveisOuNao(PessoaFisicaServiceAdapterDefault implDefault,
			@DadosSensiveis PessoaFisicaServiceAdapterDadosSensiveis implDadosSensiveis) {
		PessoaFisicaServiceAdapter impl = implDefault;
		if (isUsaAdaptadorDadosSensiveis()) {
			impl = implDadosSensiveis;
		}
		return impl;
	}

	@Produces
	@Fibonacci
	@Named("proximoNumeroFibonacci")
	public int next() {
		int aux = anterior;
		anterior = atual;
		atual += aux;
		return atual;
	}

}
