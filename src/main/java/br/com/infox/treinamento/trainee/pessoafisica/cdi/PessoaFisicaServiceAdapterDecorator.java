package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;

@Decorator
public abstract class PessoaFisicaServiceAdapterDecorator implements PessoaFisicaServiceAdapter {
	private static final Logger LOG = Logger.getLogger("trainee.cdi.dados_sensiveis");

	private int quantidadeAcessos = 0;

	@Inject
	@Delegate
	private PessoaFisicaServiceAdapter pessoaFisicaServiceAdapter;

	@Inject
	private PessoaFisicaServiceAdapterRouter pessoaFisicaServiceAdapterRouter;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct " + getClass().getSimpleName());
	}

	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy " + getClass().getSimpleName());
	}

	@Override
	public List<PessoaFisica> recuperarPessoas() {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A " + getClass().getSimpleName() + " => " + this.quantidadeAcessos);
		List<PessoaFisica> recuperarPessoas = pessoaFisicaServiceAdapter.recuperarPessoas();
		if (pessoaFisicaServiceAdapterRouter.isUsaAdaptadorDadosSensiveis()) {
			recuperarPessoas = recuperarPessoas.stream().map(this::esconderDados).collect(Collectors.toList());
		}
		return recuperarPessoas;
	}

	private String ocultarDados(String string) {
		char firstChar = string.charAt(0);
		char lastChar = string.charAt(string.length() - 1);
		return firstChar + "*********" + lastChar;
	}

	private PessoaFisica esconderDados(PessoaFisica pessoaFisica) {
		PessoaFisica comDadosOcultos = new PessoaFisica();
		comDadosOcultos.setCpf(ocultarDados(pessoaFisica.getCpf()));
		comDadosOcultos.setEmail(ocultarDados(pessoaFisica.getEmail()));
		comDadosOcultos.setName(ocultarDados(pessoaFisica.getName()));
		comDadosOcultos.setPhoneNumber(ocultarDados(pessoaFisica.getPhoneNumber()));
		return comDadosOcultos;
	}

}
