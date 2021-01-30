package br.com.infox.treinamento.trainee.concursolicitatorio;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ConcursoLicitatorio {
	
	@NotNull
	@Size(min = 1, max = 25)
	private String nome;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String descricao;
	
	@NotNull
	private Date dataDisponibilizacao;
	
	@NotNull
	private Date dataLimite;
	    //Deve possuir uma validação que impede que seja anteriro ou igual à data de disponibilização
	
	@NotNull
	@Pattern(regexp = "[0-9]*", message = "Must contain only numbers")
	private String quantidade;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getDataDisponibilizacao() {
		return dataDisponibilizacao;
	}
	
	public void setDataDisponibilizacao(Date dataDisponibilizacao) {
		this.dataDisponibilizacao = dataDisponibilizacao;
	}
	
	public Date getDataLimite() {
		return dataLimite;
	}
	
	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}
	
	public String getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

}
