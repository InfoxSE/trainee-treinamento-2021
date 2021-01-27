package br.com.infox.treinamento.trainee.pessoafisica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostLoad;
import javax.persistence.PostRemove;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoa_fisica", uniqueConstraints = @UniqueConstraint(name = "uk_pessoa_fisica_01", columnNames = "cd_cpf"))
@Getter
@Setter
@EntityListeners(PessoaFisicaJpaListener.class)
public class PessoaFisica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa_fisica")
	private Long id;

	@NotNull
	@Size(min = 11, max = 11)
	@CPF
	@Column(name = "cd_cpf")
	private String cpf;

	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "nm_pessoa_fisica")
	private String name;

	@NotNull
	@Column(name = "dt_birth_date")
	private Date birthDate;

	@OrderBy("tipoMeioContato ASC, contato ASC")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_pessoa_fisica", nullable = false)
	private List<MeioContato> meiosContato = new ArrayList<>(0);

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "tb_pessoa_endereco",
		joinColumns = @JoinColumn(referencedColumnName = "id_pessoa_fisica", name = "id_pessoa_fisica_j"),
		inverseJoinColumns = @JoinColumn(referencedColumnName = "id_endereco", name = "id_endereco_j")
	)
	private List<Endereco> enderecos = new ArrayList<>(0);

	private boolean isStringBlank(String str) {
		return Optional.ofNullable(str).map(String::trim).map(String::isEmpty).orElse(true);
	}

	@PostLoad
	protected void aposRecuperar() {
//		for (Iterator<MeioContato> iterator = getMeiosContato().iterator(); iterator.hasNext();) {
//			MeioContato m = iterator.next();
//			switch (m.getTipoMeioContato()) {
//			case EM:
//				if (isStringBlank(getEmail())) {
//					setEmail(m.getContato());
//				}
//				break;
//			case TF:
//				if (isStringBlank(getPhoneNumber())) {
//					setPhoneNumber(m.getContato());
//				}
//				break;
//			default:
//				break;
//			}
//		}
	}

	@PrePersist
	protected void antesPersistir() {
//		PessoaFisica objetoASerValidado = this;
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//		Set<ConstraintViolation<PessoaFisica>> constraintViolations = validator.validate(objetoASerValidado, Default.class);
//		if (constraintViolations.isEmpty()) {
//			MeioContato telefone = new MeioContato();
//			telefone.setTipoMeioContato(TipoMeioContato.TF);
//			telefone.setContato(getPhoneNumber());
//			getMeiosContato().add(telefone);
//
//			MeioContato email = new MeioContato();
//			email.setTipoMeioContato(TipoMeioContato.EM);
//			email.setContato(getEmail());
//			getMeiosContato().add(email);
//		} else {
//			Set<ConstraintViolation<?>> constraintViolations2 = new TreeSet<>(constraintViolations);
//			throw new ConstraintViolationException("Falha ao persistir", constraintViolations2);
//		}
	}

	@PreUpdate
	protected void antesAtualizar() {
//		PessoaFisica objetoASerValidado = this;
//		Set<ConstraintViolation<PessoaFisica>> constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(objetoASerValidado, Default.class);
//		if (constraintViolations.isEmpty()) {
//			for (Iterator<MeioContato> iterator = getMeiosContato().iterator(); iterator.hasNext();) {
//				MeioContato m = iterator.next();
//				switch (m.getTipoMeioContato()) {
//				case EM:
//					m.setContato(getEmail());
//					break;
//				case TF:
//					m.setContato(getPhoneNumber());
//					break;
//				default:
//					break;
//				}
//			}
//		} else {
//			Set<ConstraintViolation<?>> constraintViolations2 = new TreeSet<>(constraintViolations);
//			throw new ConstraintViolationException("Falha ao persistir", constraintViolations2);
//		}
	}

	@PreRemove
	protected void antesDeRemover() {

	}

	@PostRemove
	protected void aposRemover() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaFisica other = (PessoaFisica) obj;

		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

}
