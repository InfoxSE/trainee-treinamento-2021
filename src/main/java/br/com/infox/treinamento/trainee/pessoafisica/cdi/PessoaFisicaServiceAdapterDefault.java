package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;

import br.com.infox.treinamento.trainee.interceptors.MethodAccessLog;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisicaService;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisicaSingletonEJB;

@MethodAccessLog
public class PessoaFisicaServiceAdapterDefault implements PessoaFisicaServiceAdapter {

	private static final Logger LOG = Logger.getLogger("trainee.cdi.default");

	private int quantidadeAcessos = 0;

	@EJB
	private PessoaFisicaService pessoaFisicaService;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct " + getClass().getSimpleName());
	}

	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy " + getClass().getSimpleName());
	}

	@Override
	public void registrar(PessoaFisica novaPessoa) {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A " + getClass().getSimpleName() + " => " + this.quantidadeAcessos);
		pessoaFisicaService.registrar(novaPessoa);
	}

	@Override
	public List<PessoaFisica> recuperarPessoas() {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A " + getClass().getSimpleName() + " => " + this.quantidadeAcessos);
		return pessoaFisicaService.recuperarPessoas();
	}

}
