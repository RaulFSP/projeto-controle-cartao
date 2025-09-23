package io.github.app.domain.recebedor;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recebedores")
public class Recebedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoRecebedor tipoRecebedor;

	public Recebedor() {
	}

	private Recebedor(Builder builder) {
		this.id = builder.id;
		this.nome = builder.nome;
		this.tipoRecebedor = builder.tipoRecebedor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tipoRecebedor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recebedor other = (Recebedor) obj;
		return Objects.equals(id, other.id) && tipoRecebedor == other.tipoRecebedor;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		Long id;
		String nome;
		TipoRecebedor tipoRecebedor;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder tipoRecebedor(TipoRecebedor tipoRecebedor) {
			this.tipoRecebedor = tipoRecebedor;
			return this;
		}

		public Recebedor build() {
			return new Recebedor(this);
		}
	}
}
