package br.com.infox.treinamento.trainee.pessoafisica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PessoaFisicaControllerJSF implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger("trainee.pessoafisicacontroller");
	
	private PessoaFisica novaPessoa; 
	
	private List<PessoaFisica> pessoas;
	
	@PostConstruct
	public void init() {
		LOG.info("PostConstruct PessoaFisicaControllerJSF");
		this.novaPessoa = new PessoaFisica();
	}
	
	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy PessoaFisicaControllerJSF");
	}

	public void registrar() {
		if (pessoas == null) {
			this.pessoas = new ArrayList<>(0);
		}
		getPessoas().add(getNovaPessoa());
		this.novaPessoa = null;
		LOG.info("PESSOAS DA LISTA");
		for (PessoaFisica pessoaFisica : pessoas) {
			LOG.info("NOME => " + pessoaFisica.getName());
			LOG.info("CPF => " + pessoaFisica.getCpf());
			LOG.info("EMAIL = > " + pessoaFisica.getEmail());
			LOG.info("NASCIMENTO => " + pessoaFisica.getBirthDate());
			LOG.info("TELEFONE => " + pessoaFisica.getPhoneNumber());
		}
	}
	
	public PessoaFisica getNovaPessoa() {
		return novaPessoa;
	}

	public List<PessoaFisica> getPessoas() {
		return pessoas;
	}
}
