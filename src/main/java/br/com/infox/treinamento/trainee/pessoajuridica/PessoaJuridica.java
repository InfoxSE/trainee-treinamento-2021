package br.com.infox.treinamento.trainee.pessoajuridica;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PessoaJuridica {
	
	@NotNull
    @Size(min = 14, max = 14)
    @Pattern(regexp = "[0-9]+", message = "Must contain only numbers")
    private String cnpj;

    @NotNull
    @Size(min = 1, max = 25)
    private String nome;
    
    @NotNull
    @Size(min = 1, max = 40)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String razao_social;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

    
    
}
