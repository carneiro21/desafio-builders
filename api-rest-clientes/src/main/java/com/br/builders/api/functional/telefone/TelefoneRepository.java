package com.br.builders.api.functional.telefone;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.builders.api.entity.TelefoneEntity;

public interface TelefoneRepository extends JpaRepository<TelefoneEntity, Long>, TelefoneRepositoryCustom{

	TelefoneEntity findByTelefoneAndDddAndDdi(String telefone, String ddd, String ddi);
}
