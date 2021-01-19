package br.com.infox.treinamento.trainee.pessoafisica;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PessoaFisicaControllerJSF implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger("trainee.member");

	@EJB
	private PessoaFisicaService pessoaFisicaService;

	private PessoaFisica novaPessoa;

	private List<PessoaFisica> pessoas;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct PessoaFisicaControllerJSF");
		novoCadastro();
		pessoas = pessoaFisicaService.recuperarPessoas();
	}
	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy PessoaFisicaControllerJSF");
	}

	public void registrar() {
		pessoaFisicaService.registrar(getNovaPessoa());
		pessoas = pessoaFisicaService.recuperarPessoas();
		novoCadastro();
	}
	private void novoCadastro() {
		this.novaPessoa = new PessoaFisica();
	}

	public PessoaFisica getNovaPessoa() {
		return novaPessoa;
	}

	public List<PessoaFisica> getPessoas() {
		return pessoas;
	}

}
