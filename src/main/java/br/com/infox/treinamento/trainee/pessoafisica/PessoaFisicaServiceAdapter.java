package br.com.infox.treinamento.trainee.pessoafisica;

import java.util.List;

public interface PessoaFisicaServiceAdapter {

	List<PessoaFisica> recuperarPessoas();

	void registrar(PessoaFisica novaPessoa);

}
