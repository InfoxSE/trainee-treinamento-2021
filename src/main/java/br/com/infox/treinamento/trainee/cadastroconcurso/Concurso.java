package br.com.infox.treinamento.trainee.cadastroconcurso;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Concurso {
	
	@NotNull
    @Size(min = 1, max = 25)
	private String nome;
	
	@NotNull
    @Size(min = 1, max = 200)
	private String descricao;
	
	@NotNull
	private Calendar dataDisponibilizacao = Calendar.getInstance();
	
	@NotNull
	private Calendar dataLimite = Calendar.getInstance();
	
	@NotNull
	@Pattern(regexp = "[0-9]+", message = "Must contain only numbers")
	private String quantidade;
	
	
	
	public Concurso() {
		dataLimite.add(Calendar.DAY_OF_MONTH, 1); //somando um dia para que no form o validator não dê erro para data igual a disponibilizacao
	}
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
	public Calendar getDataDisponibilizacao() {
		return dataDisponibilizacao;
	}
	public void setDataDisponibilizacao(Calendar dataDisponibilizacao) {
		this.dataDisponibilizacao = dataDisponibilizacao;
	}
	public Calendar getDataLimite() {
		return dataLimite;
	}
	public void setDataLimite(Calendar dataLimite) {
		this.dataLimite = dataLimite;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	
	

}
