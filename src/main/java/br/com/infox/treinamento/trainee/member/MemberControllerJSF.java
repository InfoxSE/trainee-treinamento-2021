package br.com.infox.treinamento.trainee.member;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.infox.treinamento.trainee.model.Member;

@ManagedBean
@RequestScoped
public class MemberControllerJSF {
	
	private static final Logger LOG = Logger.getLogger("br.com.infox.treinamento.trainee.member");;
	
	private Member membroNovo; 
	
	@PostConstruct
	public void init() {
		this.membroNovo = new Member();
	}
	
	public void registrar() {
		LOG.info("NOME => " + this.membroNovo.getName());
		LOG.info("EMAIL = > " + this.membroNovo.getEmail());
		LOG.info("TELEFONE => " + this.membroNovo.getPhoneNumber());
	}
	
	public Member getMembroNovo() {
		return membroNovo;
	}
	
}
