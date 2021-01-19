package br.com.infox.treinamento.trainee.pessoafisica;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PessoaFisicaStatelessEJB implements PessoaFisicaService {

	private static final Logger LOG = Logger.getLogger("trainee.slsb");

	private int quantidadeAcessos = 0;
	@EJB
	private PessoaFisicaSingletonEJB pessoaFisicaSingletonEJB;

	public void registrar(PessoaFisica novaPessoa) {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A STATELESS SESSION BEAN => "+this.quantidadeAcessos);
		pessoaFisicaSingletonEJB.registrar(novaPessoa);
	}

	public List<PessoaFisica> recuperarPessoas() {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A STATELESS SESSION BEAN => "+this.quantidadeAcessos);
		return pessoaFisicaSingletonEJB.recuperarPessoas();
	}

}
