package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;

@Named("pessoaFisicaController")
@RequestScoped
public class PessoaFisicaControllerCDI implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger("trainee.pessoafisica.cdi");

	@Inject
	@Router
	private PessoaFisicaServiceAdapter pessoaFisicaServiceAdapter;
	@Inject
	private PessoaFisicaServiceAdapterRouter adapterRouter;

	private PessoaFisica novaPessoa;

	private List<PessoaFisica> pessoas;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct "+getClass().getSimpleName());
		novoCadastro();
		pessoas = pessoaFisicaServiceAdapter.recuperarPessoas();
	}
	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy "+getClass().getSimpleName());
	}

	public void alternarAdapter() {
		adapterRouter.setUsaAdaptadorDadosSensiveis(!adapterRouter.isUsaAdaptadorDadosSensiveis());
		pessoas = pessoaFisicaServiceAdapter.recuperarPessoas();
	}

	public void registrar() {
		pessoaFisicaServiceAdapter.registrar(getNovaPessoa());
		pessoas = pessoaFisicaServiceAdapter.recuperarPessoas();
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
