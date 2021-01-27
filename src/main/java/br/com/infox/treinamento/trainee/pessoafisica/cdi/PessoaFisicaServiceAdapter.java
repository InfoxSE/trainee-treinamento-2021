package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.util.List;

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;

public interface PessoaFisicaServiceAdapter {

	List<PessoaFisica> recuperarPessoas();

	void registrar(PessoaFisica novaPessoa);

	void remover(Long id);

}
