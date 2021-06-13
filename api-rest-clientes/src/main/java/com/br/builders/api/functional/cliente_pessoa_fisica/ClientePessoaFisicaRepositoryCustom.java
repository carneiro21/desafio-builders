package com.br.builders.api.functional.cliente_pessoa_fisica;

import java.util.List;

import com.br.builders.api.dto.FiltroClientePessoaFisicaDTO;
import com.br.builders.api.entity.ClientePessoaFisicaEntity;

public interface ClientePessoaFisicaRepositoryCustom {

	public List<ClientePessoaFisicaEntity> pesquisarCliente(FiltroClientePessoaFisicaDTO filtro);
	
	public ClientePessoaFisicaEntity buscarClientePorCPF(String cpf);
}
