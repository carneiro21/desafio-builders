package com.br.builders.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private CidadeDTO cidade;
	
	private String rua;
	
	private String bairro;
	
	private String logradouro;
	
	private Integer numero;
	
	private Integer cep;
	
	private String complemento;

}
