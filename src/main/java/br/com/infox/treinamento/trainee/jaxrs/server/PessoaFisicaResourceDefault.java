package br.com.infox.treinamento.trainee.jaxrs.server;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.infox.treinamento.trainee.jaxrs.api.PessoaFisicaResource;
import br.com.infox.treinamento.trainee.pessoafisica.cdi.PessoaFisicaServiceAdapter;

@RequestScoped
public class PessoaFisicaResourceDefault implements PessoaFisicaResource {

	private Long id;
	@Inject
	private PessoaFisicaServiceAdapter pessoaFisicaServiceAdapter;

	@Override
	public void remover() {
		pessoaFisicaServiceAdapter.remover(id);
	}

	public PessoaFisicaResource id(Long id) {
		this.id=id;
		return this;
	}

}
