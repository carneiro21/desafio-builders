package com.br.builders.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestEnderecoDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RequestCidadeDTO cidade;
	
	private String rua;
	
	private String bairro;
	
	private String logradouro;
	
	private Integer numero;
	
	private Integer cep;
	
	private String complemento;

}
