package br.com.infox.treinamento.trainee.pessoajuridica;

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
public class PessoaJuridicaControllerJSF implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private PessoaJuridica novaPessoaJuridica;

	private List<PessoaJuridica> pessoasJuridicas;
	
	@PostConstruct
	public void init() {
		this.novaPessoaJuridica = new PessoaJuridica();
	}
	
	@PreDestroy
	public void destroy() {
	}

	public void registrar() {
		if (pessoasJuridicas == null) {
			this.pessoasJuridicas = new ArrayList<>(0);
		}
		getPessoasJuridicas().add(getNovaPessoaJuridica());
		this.novaPessoaJuridica = new PessoaJuridica();
	}
	
	public PessoaJuridica getNovaPessoaJuridica() {
		return novaPessoaJuridica;
	}
	
	public List<PessoaJuridica> getPessoasJuridicas() {
		return pessoasJuridicas;
	}

}
