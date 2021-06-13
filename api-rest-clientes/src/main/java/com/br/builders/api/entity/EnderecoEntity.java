package com.br.builders.api.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "endereco", catalog = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "CEP"))
public class EnderecoEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_ENDERECO", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CIDADE", nullable = false)
	private CidadeEntity cidade;
	
	@Column(name = "RUA", nullable = false, length = 200)
	private String rua;
	
	@Column(name = "BAIRRO", nullable = false, length = 200)
	private String bairro;
	
	@Column(name = "LOGRADOURO", length = 100)
	private String logradouro;
	
	@Column(name = "NUMERO", nullable = false)
	private Integer numero;
	
	@Column(name = "CEP", unique = true, nullable = false)
	private Integer cep;
	
	@Column(name = "COMPLEMENTO", nullable = true, length = 200)
	private String complemento;

}
