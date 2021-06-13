package com.br.builders.api.functional.cliente_pessoa_fisica;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.builders.api.entity.ClientePessoaFisicaEntity;

public interface ClientePessoaFisicaRepository extends JpaRepository<ClientePessoaFisicaEntity, Long>, ClientePessoaFisicaRepositoryCustom {
	

}
