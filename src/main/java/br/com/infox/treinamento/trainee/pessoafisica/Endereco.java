package br.com.infox.treinamento.trainee.pessoafisica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_endereco")
@Getter
@Setter
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Integer id;

	@NotNull
	@Size(min=1,max = 100)
	@Column(name = "nm_cidade")
	private String cidade;

	@NotNull
	@Size(max = 2, min = 2)
	@Column(name = "cd_uf")
	private String uf;

	@NotNull
	@Size(min=1,max = 100)
	@Column(name = "nm_logradouro")
	private String logradouro;

	@NotNull
	@Size(min=1,max = 15)
	@Column(name = "nr_logradouro")
	private String numero;

	@Size(min=1,max = 100)
	@Column(name = "ds_complemento")
	private String complemento;

	@NotNull
	@Size(min=1,max = 100)
	@Column(name = "nm_bairro")
	private String bairro;

	@NotNull
	@Size(min = 8, max = 8)
	@Column(name = "nr_cep")
	private String cep;

}