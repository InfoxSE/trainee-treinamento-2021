package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.infox.treinamento.trainee.interceptors.MethodAccessLog;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisicaService;

@MethodAccessLog
public class PessoaFisicaServiceAdapterDefault implements PessoaFisicaServiceAdapter, Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger("trainee.cdi.default");

	private int quantidadeAcessos = 0;

	@EJB
	private PessoaFisicaService pessoaFisicaService;
	@Inject
	private Event<PessoaFisicaEvent> dispatcher;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct " + getClass().getSimpleName());
	}

	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy " + getClass().getSimpleName());
	}

	@Override
	public void registrar(PessoaFisica novaPessoa) {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A " + getClass().getSimpleName() + " => " + this.quantidadeAcessos);
		PessoaFisicaEventType tipoEvento = PessoaFisicaEventType.INSERT;
		if (novaPessoa.getId() != null) {
			tipoEvento = PessoaFisicaEventType.UPDATE;
		}
		pessoaFisicaService.registrar(novaPessoa);
		dispatcher.fire(new PessoaFisicaEvent(tipoEvento, novaPessoa));
	}

	@Override
	public List<PessoaFisica> recuperarPessoas() {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A " + getClass().getSimpleName() + " => " + this.quantidadeAcessos);
		return pessoaFisicaService.recuperarPessoas();
	}

	@Override
	public void remover(Long idPessoa) {
		PessoaFisica pessoa = pessoaFisicaService.remover(idPessoa);
		dispatcher.fire(new PessoaFisicaEvent(PessoaFisicaEventType.REMOVE, pessoa));
	}

}
