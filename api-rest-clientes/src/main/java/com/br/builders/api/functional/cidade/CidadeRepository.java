package com.br.builders.api.functional.cidade;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.builders.api.entity.CidadeEntity;

public interface CidadeRepository extends JpaRepository<CidadeEntity, Long>, CidadeRepositoryCustom{

	public CidadeEntity findByNomeCidade(String nomeCidade);
}
