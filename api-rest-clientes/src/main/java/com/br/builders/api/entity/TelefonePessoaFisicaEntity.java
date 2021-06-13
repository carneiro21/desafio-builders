package com.br.builders.api.entity;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "telefone_pessoa_fisica", catalog = "clientes")
public class TelefonePessoaFisicaEntity {

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "idTelefone", column = @Column(name = "ID_TELEFONE", nullable = false, precision = 12, scale = 0)),
		@AttributeOverride(name = "idPessoaFisica", column = @Column(name = "ID_PESSOA_FISICA", nullable = false, precision = 12, scale = 0)) })
	private TelefonePessoaFisicaIdEntity id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TELEFONE", nullable = false, insertable = false, updatable = false)
	private TelefoneEntity telefone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PESSOA_FISICA", nullable = false, insertable = false, updatable = false)
	private PessoaFisicaEntity pessoaFisica;
	
	@Column(name = "DT_ATUALIZACAO", nullable = false, length = 7)
	private LocalDate dataAtualizacao;
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TelefonePessoaFisicaEntity other = (TelefonePessoaFisicaEntity) obj;
		if (dataAtualizacao == null) {
			if (other.dataAtualizacao != null)
				return false;
		} else if (!dataAtualizacao.equals(other.dataAtualizacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}
	
	
}
