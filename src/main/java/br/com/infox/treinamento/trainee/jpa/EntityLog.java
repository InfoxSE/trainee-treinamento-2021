package br.com.infox.treinamento.trainee.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_log")
@Data
@EqualsAndHashCode(of = "id")
public class EntityLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_log")
	private Integer id;
	@Column(name = "id_pagina", length = 255)
	@Size(max = 255)
	private String urlRequisicao;
	@Column(name = "vl_ip", length = 32)
	@Size(max = 32)
	private String ip;
	@Column(name = "nm_entidade", length = 255)
	@Size(max = 255)
	private String nomeEntidade;
	@Column(name = "vl_id_entidade", length = 32)
	@Size(max = 32)
	private String idEntidade;
	@Column(name = "tp_operacao")
	@Enumerated(EnumType.STRING)
	private TipoOperacaoLogEnum operacao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_log", nullable = false)
	@NotNull
	private Date dataLog;
	@JoinColumn(name="id_log")
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<EntityLogDetail> entityLogDetailList = new ArrayList<>(0);

	@PrePersist
	protected void beforePersist() {
		this.dataLog = new Date();
	}

	@Override
	public String toString() {
		return nomeEntidade;
	}

}
