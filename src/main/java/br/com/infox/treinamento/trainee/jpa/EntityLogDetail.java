package br.com.infox.treinamento.trainee.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_log_detalhe")
@Data @EqualsAndHashCode(of="id")
public class EntityLogDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalhe_log")
	private Integer id;
	@Column(name = "nm_atributo", length = 255)
	@Size(max = 255)
	private String nomeAtributo;
	@Column(name = "vl_atributo")
	private String valor;

	@Override
	public String toString() {
		return nomeAtributo;
	}

}
