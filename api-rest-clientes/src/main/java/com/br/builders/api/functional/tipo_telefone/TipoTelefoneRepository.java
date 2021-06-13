package com.br.builders.api.functional.tipo_telefone;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.builders.api.entity.TipoTelefoneEntity;

public interface TipoTelefoneRepository extends JpaRepository<TipoTelefoneEntity, Long>, TipoTelefoneRepositoryCustom{

	TipoTelefoneEntity findByNomeTipoTelefone(String nomeTipoTelefone);
}
