package br.com.infox.treinamento.trainee.pessoajuridica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;

@ManagedBean
@ViewScoped
public class PessoaJuridicaControllerJSF implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger("trainee.member");

	private PessoaJuridica novaPessoaJuridica;
	private List<PessoaJuridica> lista_pessoajuridica;
	
	@PostConstruct
	public void init() {
		LOG.info("PostConstruct PessoaJuridicaControllerJSF");
		this.novaPessoaJuridica = new PessoaJuridica();
	}
	
	public void registrar() {
		if (lista_pessoajuridica == null) {
			this.lista_pessoajuridica = new ArrayList<>(0);
		}
		getLista_pessoajuridica().add(getNovaPessoaJuridica());
		this.novaPessoaJuridica = null;
		LOG.info("PESSOAS JURÍDICAS DA LISTA");
		for (PessoaJuridica pessoaJuridica : lista_pessoajuridica) {
			LOG.info("NOME => " + pessoaJuridica.getNome());
			LOG.info("RAZAO SOCIAL => " + pessoaJuridica.getRazao_social());
			LOG.info("CNPJ => " + pessoaJuridica.getCnpj());
		}
	}

	public PessoaJuridica getNovaPessoaJuridica() {
		return novaPessoaJuridica;
	}

	public List<PessoaJuridica> getLista_pessoajuridica() {
		return lista_pessoajuridica;
	}


	
	

}
