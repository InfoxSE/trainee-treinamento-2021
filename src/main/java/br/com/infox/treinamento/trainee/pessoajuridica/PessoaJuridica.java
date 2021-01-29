package br.com.infox.treinamento.trainee.pessoajuridica;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PessoaJuridica {

	@NotNull
	@Size(min = 1, max = 50)
	private String nome;
	
	@NotNull
	@Size(min = 1, max = 50)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String razaoSocial;
	
	@NotNull
    @Size(min = 14, max = 14)
    @Pattern(regexp = "[0-9]*", message = "Must contain only numbers")
	private String cnpj;
	//xx.xxx.xxx/xxxx-xx 

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
