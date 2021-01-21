package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class PessoaFisicaServiceAdapterRouter implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger("trainee.cdi.adapter_router");

	private boolean usaAdaptadorDadosSensiveis = false;
	@Named("fiboAnterior")
	@Produces
	@FibonacciAnterior
	private int anterior = 0;
	@Named("fiboAtual")
	@Produces
	@FibonacciAtual
	private int atual = 1;
	@Inject
	private BeanManager beanManager;

	public boolean isUsaAdaptadorDadosSensiveis() {
		return usaAdaptadorDadosSensiveis;
	}

	public void setUsaAdaptadorDadosSensiveis(boolean usaAdaptadorDadosSensiveis) {
		this.usaAdaptadorDadosSensiveis = usaAdaptadorDadosSensiveis;
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
