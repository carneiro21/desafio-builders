package com.br.builders.api.dto;


import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPessoaFisicaDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RequestEnderecoDTO endereco;
	
	private Set<RequestTelefoneDTO> telefones;
	
	private String nome;
	
	private String email;
	
	private String cpf;
	
	private LocalDate dataNascimento;

}
