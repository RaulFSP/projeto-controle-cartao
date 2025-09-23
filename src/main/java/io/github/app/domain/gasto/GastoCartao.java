package io.github.app.domain.gasto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import io.github.app.domain.recebedor.Recebedor;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gastos")
public class GastoCartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Banco banco;
	private LocalDate dataOcorrencia;
	private BigDecimal valor;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_recebedor", nullable = false)
	private Recebedor recebedor;

	public GastoCartao() {
	}

	private GastoCartao(Builder builder) {
		this.id = builder.id;
		this.banco = builder.banco;
		this.dataOcorrencia = builder.dataOcorrencia;
		this.valor = builder.valor;
		this.recebedor = builder.recebedor;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public LocalDate getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(LocalDate dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Recebedor getRecebedor() {
		return recebedor;
	}

	public void setRecebedor(Recebedor recebedor) {
		this.recebedor = recebedor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GastoCartao other = (GastoCartao) obj;
		return Objects.equals(id, other.id);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		Long id;
		Banco banco;
		LocalDate dataOcorrencia;
		BigDecimal valor;
		Recebedor recebedor;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder banco(Banco banco) {
			this.banco = banco;
			return this;
		}

		public Builder dataOcorrencia(LocalDate dataOcorrencia) {
			this.dataOcorrencia = dataOcorrencia;
			return this;
		}

		public Builder valor(BigDecimal valor) {
			this.valor = valor;
			return this;
		}

		public Builder recebedor(Recebedor recebedor) {
			this.recebedor = recebedor;
			return this;
		}

		public GastoCartao build() {
			return new GastoCartao(this);
		}
	}
}
