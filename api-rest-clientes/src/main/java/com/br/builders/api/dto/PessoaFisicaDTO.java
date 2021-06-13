package com.br.builders.api.dto;


import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisicaDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private EnderecoDTO endereco;
	
	private Set<TelefoneDTO> telefones;
	
	private String nome;
	
	private String email;
	
	private Integer idade;
	
	private String cpf;
	
	private LocalDate dataNascimento;

	public Integer getIdade() {
		if (getDataNascimento() != null) {
			this.idade = Period.between(getDataNascimento(), LocalDate.now()).getYears();
		}
		return idade;
	}

}
