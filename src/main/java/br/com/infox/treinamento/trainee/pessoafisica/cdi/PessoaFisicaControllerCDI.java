package br.com.infox.treinamento.trainee.pessoafisica;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Named("pessoaFisicaController")
@RequestScoped
public class PessoaFisicaControllerCDI implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger("trainee.pessoafisica.cdi");

	@Inject
	private PessoaFisicaService pessoaFisicaService;

	private PessoaFisica novaPessoa;

	private List<PessoaFisica> pessoas;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct "+getClass().getSimpleName());
		novoCadastro();
		pessoas = getPessoaFisicaService().recuperarPessoas();
	}
	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy "+getClass().getSimpleName());
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
