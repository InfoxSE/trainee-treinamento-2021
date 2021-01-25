package br.com.infox.treinamento.trainee.pessoafisica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_meio_contato")
@Getter @Setter
public class MeioContato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_meio_contato")
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tp_meio_contato")
	private TipoMeioContato tipoMeioContato;

	@NotNull
	@Size(min=1)
	@Column(name = "vl_meio_contato")
	private String contato;

}
