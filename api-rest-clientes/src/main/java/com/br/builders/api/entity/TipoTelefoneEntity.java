package com.br.builders.api.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tipo_telefone", catalog = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "NOME_TIPO_TELEFONE"))
public class TipoTelefoneEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_TELEFONE", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "NOME_TIPO_TELEFONE", unique = true, nullable = false, length = 200)
	private String nomeTipoTelefone;
	
}
