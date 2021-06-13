package com.br.builders.api.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class TelefonePessoaFisicaIdEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID_TELEFONE", nullable = false, precision = 12, scale = 0)
	private Long idTelefone;
	
	@Column(name = "ID_PESSOA_FISICA", nullable = false, precision = 3, scale = 0)
	private Long idPessoaFisica;

}
