package br.com.infox.treinamento.trainee.jaxrs.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;

@Produces({
	MediaType.APPLICATION_JSON,
	MediaType.APPLICATION_XML
})
@Consumes(MediaType.APPLICATION_JSON)
public interface PessoasFisicasResource {

	@POST
	void inserirPessoa(PessoaFisica pessoaFisica);

	@GET
	@Consumes(MediaType.WILDCARD)
	List<PessoaFisica> listar();

	@DELETE
	@Path("/{id}")
	void remover(@PathParam("id") Long id);

	@Path("/{id}/pf")
	PessoaFisicaResource resource(@PathParam("id") Long id);

}
