package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;
import lombok.Getter;

@TraineeModel
public class PessoaFisicaControllerCDI implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger("trainee.pessoafisica.cdi");

	@Inject
	private PessoaFisicaServiceAdapter pessoaFisicaServiceAdapter;
	@Inject
	private PessoaFisicaServiceAdapterRouter adapterRouter;
	@Inject
	private PessoaFisicaFormController pessoaFisicaFormController;

	@Getter
	private List<PessoaFisica> pessoas;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct "+getClass().getSimpleName());
		pessoas = pessoaFisicaServiceAdapter.recuperarPessoas();
	}
	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy "+getClass().getSimpleName());
	}

	public void capturarEventoPessoaFisica(@Observes PessoaFisicaEvent evento) {
		LOG.info(String.format("OPERAÇÃO DE %s EM PESSOA DE CPF %s", evento.getType(), evento.getPessoaFisica().getCpf()));
		pessoas = pessoaFisicaServiceAdapter.recuperarPessoas();
	}

	public void alternarAdapter() {
		adapterRouter.setUsaAdaptadorDadosSensiveis(!adapterRouter.isUsaAdaptadorDadosSensiveis());
	}
	public void remover(PessoaFisica pessoa) {
		pessoaFisicaServiceAdapter.remover(pessoa);
	}
	public void editar(PessoaFisica pessoa) {
		pessoaFisicaFormController.iniciarEdicao(pessoa);
	}

}
