package com.br.builders.api.entity;


import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "cliente_pessoa_fisica", catalog = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "ID_PESSOA_FISICA"))
public class ClientePessoaFisicaEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE_PESSOA_FISICA", unique = true, nullable = false)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
	@JoinColumn(name = "ID_PESSOA_FISICA", unique = true, nullable = false)
	private PessoaFisicaEntity pessoaFisica;
	
	@Column(name = "ST_ATIVO", nullable = false)
	private boolean situacaoAtivo;
	
	@Column(name = "INFO_COMPLEMENTAR", length = 300)
	private String informacaoComplementar;
	
	@Column(name = "DATA_ATUALIZACAO", nullable = false, length = 19)
	private LocalDateTime dataAtualizacao;

}
