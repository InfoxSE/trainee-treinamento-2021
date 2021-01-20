package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.inject.Alternative;

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisicaService;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisicaServiceAdapter;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisicaSingletonEJB;

@Alternative
public class PessoaFisicaServiceAdapterDadosSensiveis implements PessoaFisicaServiceAdapter {

	private static final Logger LOG = Logger.getLogger("trainee.cdi.dados_sensiveis");

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
		return pessoaFisicaService.recuperarPessoas().stream().map(this::esconderDados)
				.collect(Collectors.toList());
	}

	private String ocultarDados(String string) {
		char firstChar = string.charAt(0);
		char lastChar = string.charAt(string.length()-1);
		return firstChar+"*********"+lastChar;
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
