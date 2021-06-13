package com.br.builders.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestClientePessoaFisicaDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RequestPessoaFisicaDTO pessoaFisica;
	
	private boolean situacaoAtivo;
	
	private String informacaoComplementar;

}
