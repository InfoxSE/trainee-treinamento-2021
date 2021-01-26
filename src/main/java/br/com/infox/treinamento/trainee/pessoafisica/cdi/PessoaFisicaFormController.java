package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.infox.treinamento.trainee.pessoafisica.MeioContato;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;
import lombok.Getter;

@Named
@ConversationScoped
public class PessoaFisicaFormController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger("trainee.pessoafisica.cdi");

	@Inject
	private PessoaFisicaServiceAdapter pessoaFisicaServiceAdapter;
	@Inject
	private Conversation conversation;

	@Getter
	private PessoaFisica pessoaEmEdicao;
	@Getter
	private MeioContato meioContatoEmEdicao;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct "+getClass().getSimpleName());
	}
	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy "+getClass().getSimpleName());
	}

	public boolean isRegistroEmEdicao() {
		return this.pessoaEmEdicao != null;
	}
	public boolean isRegistroMeioContatoEmEdicao() {
		return this.meioContatoEmEdicao != null;
	}

	public void iniciarNovoRegistro() {
		conversation.begin();
		conversation.setTimeout(600_000);
		this.pessoaEmEdicao = new PessoaFisica();
	}

	public void iniciarEdicao(PessoaFisica pessoaFisica) {
		conversation.begin();
		conversation.setTimeout(600_000);
		this.pessoaEmEdicao = pessoaFisica;
	}

	public void incluirNovoContato() {
		this.meioContatoEmEdicao = new MeioContato();
	}
	public void iniciarEdicaoMeioContato(MeioContato meioContato) {
		this.meioContatoEmEdicao = meioContato;
	}
	public void removerMeioContato(MeioContato meioContato) {
		getPessoaEmEdicao().getMeiosContato().remove(meioContato);
	}
	public void registrarMeioContato() {
		if (this.meioContatoEmEdicao != null && this.meioContatoEmEdicao.getId() == null) {
			getPessoaEmEdicao().getMeiosContato().add(getMeioContatoEmEdicao());
		}
		this.meioContatoEmEdicao = null;
	}

	public void registrar() {
		try {
			pessoaFisicaServiceAdapter.registrar(getPessoaEmEdicao());
			conversation.end();
			this.pessoaEmEdicao = null;
		} catch(Exception e) {
			throw e;
		}
	}

}
