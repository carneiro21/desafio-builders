package com.br.builders.api.functional.pessoa_fisica;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.builders.api.entity.PessoaFisicaEntity;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaEntity, Long>, PessoaFisicaRepositoryCustom{

}
