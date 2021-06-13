package com.br.builders.api.dto;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePessoaFisicaDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private PessoaFisicaDTO pessoaFisica;
	
	private boolean situacaoAtivo;
	
	private String informacaoComplementar;
	
	private LocalDateTime dataAtualizacao;

}
