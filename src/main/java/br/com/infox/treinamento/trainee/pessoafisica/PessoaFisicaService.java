package br.com.infox.treinamento.trainee.pessoafisica;

import java.util.List;

import javax.ejb.Local;

@Local
public interface PessoaFisicaService {

	List<PessoaFisica> recuperarPessoas();

	void registrar(PessoaFisica novaPessoa);

	List<PessoaFisica> recuperarPessoas(Integer offset, Integer limit);

}
