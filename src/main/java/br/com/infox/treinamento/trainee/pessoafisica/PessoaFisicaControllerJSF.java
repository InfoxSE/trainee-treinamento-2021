package br.com.infox.treinamento.trainee.pessoafisica;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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
		pessoas = getPessoaFisicaService().recuperarPessoas();
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

	private PessoaFisicaService getPessoaFisicaService() {
		try {
			InitialContext initialContext = new InitialContext();
			PessoaFisicaService lookup = (PessoaFisicaService) initialContext.lookup("java:module/PessoaFisicaStatelessEJB");
			return lookup;
		} catch (NamingException e) {
			throw new IllegalStateException(e);
		}
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
