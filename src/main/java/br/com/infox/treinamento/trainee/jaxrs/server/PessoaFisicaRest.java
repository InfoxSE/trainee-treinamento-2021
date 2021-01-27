package br.com.infox.treinamento.trainee.jaxrs.server;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import br.com.infox.treinamento.trainee.jaxrs.api.PessoaFisicaResource;
import br.com.infox.treinamento.trainee.jaxrs.api.PessoasFisicasResource;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;
import br.com.infox.treinamento.trainee.pessoafisica.cdi.PessoaFisicaServiceAdapter;

@Path("/pessoaFisica")
@RequestScoped
public class PessoaFisicaRest implements PessoasFisicasResource {

	@Inject
	private PessoaFisicaServiceAdapter pessoaFisicaServiceAdapter;
	@Inject
	private PessoaFisicaResourceDefault pessoaFisicaResourceDefault;

	@Override
	public void inserirPessoa(PessoaFisica pessoaFisica) {
		pessoaFisicaServiceAdapter.registrar(pessoaFisica);
	}

	@Override
	public List<PessoaFisica> listar() {
		return pessoaFisicaServiceAdapter.recuperarPessoas();
	}

	@Override
	public void remover(Long id) {
		pessoaFisicaServiceAdapter.remover(id);
	}

	@Override
	public PessoaFisicaResource resource(Long id) {
		return pessoaFisicaResourceDefault.id(id);
	}

}
