package br.com.infox.treinamento.trainee.ws.server;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;
import br.com.infox.treinamento.trainee.pessoafisica.cdi.PessoaFisicaServiceAdapter;

@WebService
public class PessoaFisicaEndpointServer {

	@Inject
	private PessoaFisicaServiceAdapter pessoaFisicaServiceAdapter;

	@WebMethod(action = "CadastrarPessoaFisica")
	public void cadastrarPessoaFisica(PessoaFisica pessoaFisica) {
		// TODO Auto-generated method stub
		Logger log = Logger.getLogger("PessoaFisicaEndpointSoap");
		log.info("NOME => " + pessoaFisica.getName());
		log.info("CPF => " + pessoaFisica.getCpf());
		try {
			pessoaFisicaServiceAdapter.registrar(pessoaFisica);
		} catch (Exception e) {
			throw new WebServiceException(e);
		}
	}

	@WebMethod(action="Hello")
	public String sayHello(String name) {
		return "Hello, "+name+".";
	}

}
