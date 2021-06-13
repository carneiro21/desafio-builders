package com.br.builders.api.functional.uf;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.builders.api.entity.UfEntity;

public interface UfRepository extends JpaRepository<UfEntity, Long>, UfRepositoryCustom {

	public UfEntity findByNomeUf(String nomeUf);
}
