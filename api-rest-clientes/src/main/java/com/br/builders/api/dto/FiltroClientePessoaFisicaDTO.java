package com.br.builders.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FiltroClientePessoaFisicaDTO extends PaginaConsultaDTO<ClientePessoaFisicaDTO> {

	private String cpf;
	
	private String nome;
	
	private String email;
	
	private String ddi;
	
	private String ddd;
	
	private String telefone;
	
	private Integer cep;
	
	private String dataNascimentoInicio;
	
	private String dataNascimentoFim;
	
}
