package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;
import lombok.Data;
import lombok.NonNull;

@Data
public class PessoaFisicaEvent {
	@NonNull
	private final PessoaFisicaEventType type;
	@NonNull
	private final PessoaFisica pessoaFisica;
}
