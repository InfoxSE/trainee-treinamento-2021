package br.com.infox.treinamento.trainee.pessoafisica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;

@Stateful
public class PessoaFisicaStatefulEJB {

	private static final Logger LOG = Logger.getLogger("trainee.ejb");

	private List<PessoaFisica> pessoas;


	@PrePassivate
	public void prePassivate() {

	}
	@PostActivate
	public void postActivate() {

	}

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct "+getClass().getSimpleName());
	}
	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy "+getClass().getSimpleName());
	}

	public void registrar(PessoaFisica novaPessoa) {
		if (pessoas == null) {
			this.pessoas = new ArrayList<>(0);
		}
		pessoas.add(novaPessoa);
		LOG.info("PESSOAS DA LISTA");
		for (PessoaFisica pessoaFisica : pessoas) {
			LOG.info("NOME => " + pessoaFisica.getName());
			LOG.info("CPF => " + pessoaFisica.getCpf());
//			LOG.info("EMAIL => " + pessoaFisica.getEmail());
//			LOG.info("TELEFONE => " + pessoaFisica.getPhoneNumber());
		}
	}

	public List<PessoaFisica> recuperarPessoas() {
		if (pessoas == null) {
			this.pessoas = new ArrayList<>(0);
		}
		return Collections.unmodifiableList(pessoas);
	}

}
