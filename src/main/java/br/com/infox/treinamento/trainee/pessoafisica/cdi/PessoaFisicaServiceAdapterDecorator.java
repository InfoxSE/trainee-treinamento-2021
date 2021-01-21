package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;

@Decorator
public abstract class PessoaFisicaServiceAdapterDecorator implements PessoaFisicaServiceAdapter {
	private static final Logger LOG = Logger.getLogger("trainee.cdi.dados_sensiveis");

	private int quantidadeAcessos = 0;

	@Inject
	@Delegate
	private PessoaFisicaServiceAdapter pessoaFisicaServiceAdapter;

	@Inject
	private PessoaFisicaServiceAdapterRouter pessoaFisicaServiceAdapterRouter;

	@Resource
	private UserTransaction transaction;
	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct " + getClass().getSimpleName());
	}

	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy " + getClass().getSimpleName());
	}

	@Override
	public List<PessoaFisica> recuperarPessoas() {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A " + getClass().getSimpleName() + " => " + this.quantidadeAcessos);
		try {
//			try {
//				transaction.begin();
//			} catch (NotSupportedException | SystemException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			List<PessoaFisica> recuperarPessoas = pessoaFisicaServiceAdapter.recuperarPessoas();
			if (pessoaFisicaServiceAdapterRouter.isUsaAdaptadorDadosSensiveis()) {
				recuperarPessoas = recuperarPessoas.stream().map(this::esconderDados).collect(Collectors.toList());
			}
			return recuperarPessoas;
		} finally {
//			try {
//				transaction.commit();
//			} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
//					| HeuristicRollbackException | SystemException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	private String ocultarDados(String string) {
		char firstChar = string.charAt(0);
		char lastChar = string.charAt(string.length() - 1);
		return firstChar + "*********" + lastChar;
	}

	private PessoaFisica esconderDados(PessoaFisica pessoaFisica) {
//		entityManager.persist(pessoaFisica);
		pessoaFisica.setCpf(ocultarDados(pessoaFisica.getCpf()));
		pessoaFisica.setEmail(ocultarDados(pessoaFisica.getEmail()));
		pessoaFisica.setName(ocultarDados(pessoaFisica.getName()));
		pessoaFisica.setPhoneNumber(ocultarDados(pessoaFisica.getPhoneNumber()));
		return pessoaFisica;
	}

}
