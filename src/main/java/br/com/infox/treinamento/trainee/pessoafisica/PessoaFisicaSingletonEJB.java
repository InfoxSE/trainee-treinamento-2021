package br.com.infox.treinamento.trainee.pessoafisica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
@Lock(LockType.READ)
@LocalBean
public class PessoaFisicaSingletonEJB {

	private static final Logger LOG = Logger.getLogger("trainee.singlesb");

	private List<PessoaFisica> pessoas;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct "+getClass().getSimpleName());
	}
	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy "+getClass().getSimpleName());
	}

	@Lock(LockType.WRITE)
	public void registrar(PessoaFisica novaPessoa) {
		if (pessoas == null) {
			this.pessoas = new ArrayList<>(0);
		}
		pessoas.add(novaPessoa);
		LOG.info("PESSOAS DA LISTA");
		for (PessoaFisica pessoaFisica : pessoas) {
			LOG.info("NOME => " + pessoaFisica.getName());
			LOG.info("CPF => " + pessoaFisica.getCpf());
			LOG.info("EMAIL => " + pessoaFisica.getEmail());
			LOG.info("TELEFONE => " + pessoaFisica.getPhoneNumber());
		}
	}

	public List<PessoaFisica> recuperarPessoas() {
		if (pessoas == null) {
			this.pessoas = new ArrayList<>(0);
		}
		return Collections.unmodifiableList(pessoas);
	}

}
