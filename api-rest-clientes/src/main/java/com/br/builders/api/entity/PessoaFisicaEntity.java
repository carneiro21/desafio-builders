package com.br.builders.api.entity;


import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa_fisica", catalog = "clientes", uniqueConstraints = {@UniqueConstraint(columnNames = "CPF")})
public class PessoaFisicaEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_PESSOA_FISICA", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID_ENDERECO", unique = false, nullable = false)
	private EnderecoEntity endereco;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoaFisica", cascade =  CascadeType.ALL)
	private Set<TelefonePessoaFisicaEntity> telefones;
	
	@Column(name = "NOME", unique = false, nullable = false, length = 100)
	private String nome;
	
	@Column(name = "EMAIL", unique = false, nullable = false, length = 45)
	private String email;
	
	@Column(name = "CPF", unique = true, nullable = false, length = 45)
	private String cpf;
	
	@Column(name = "DATA_NASCIMENTO", length = 7, nullable = false)
	private LocalDate dataNascimento;

}
