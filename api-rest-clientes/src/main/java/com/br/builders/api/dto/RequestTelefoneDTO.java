package com.br.builders.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestTelefoneDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RequestTipoTelefoneDTO tipoTelefone;
	
	private String telefone;
	
	private String ddd;
	
	private String ddi;
	
}
