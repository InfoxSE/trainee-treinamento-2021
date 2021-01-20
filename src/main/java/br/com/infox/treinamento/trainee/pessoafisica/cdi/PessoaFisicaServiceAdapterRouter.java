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

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisicaServiceAdapter;

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
	@Router
	@Dependent
	public PessoaFisicaServiceAdapter escolherDadosSensiveisOuNao(PessoaFisicaServiceAdapterDefault implDefault,
			@DadosSensiveis PessoaFisicaServiceAdapterDadosSensiveis implDadosSensiveis) {
		PessoaFisicaServiceAdapter impl = implDefault;
		if (isUsaAdaptadorDadosSensiveis()) {
			impl = implDadosSensiveis;
		}
		LOG.info(String.format("Fornecendo %s", impl.getClass().getName()));
		return impl;
	}


	public void destruir(@Disposes @Router PessoaFisicaServiceAdapter adapter) {
		LOG.info(String.format("Removendo %s", adapter.getClass().getName()));
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
