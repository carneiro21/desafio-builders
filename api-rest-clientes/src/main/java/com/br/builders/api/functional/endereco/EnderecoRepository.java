package com.br.builders.api.functional.endereco;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.builders.api.entity.EnderecoEntity;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long>, EnderecoRepositoryCustom{

	EnderecoEntity findByCep(Integer cep);
}
