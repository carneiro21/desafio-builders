package com.br.builders.api.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.br.builders.api.dto.ClientePessoaFisicaDTO;
import com.br.builders.api.dto.PessoaFisicaDTO;
import com.br.builders.api.dto.RequestClientePessoaFisicaDTO;
import com.br.builders.api.entity.ClientePessoaFisicaEntity;
import com.br.builders.api.entity.PessoaFisicaEntity;

public class ClientePessoaFisicaFactory {

	public static Set<ClientePessoaFisicaDTO> toClientePessoaFisicaDTO(Set<ClientePessoaFisicaEntity> lisClientePessoaFisica) {

		Set<ClientePessoaFisicaDTO> clientesPessoasFisicasDTO = new HashSet<ClientePessoaFisicaDTO>();

		for (ClientePessoaFisicaEntity cliente : lisClientePessoaFisica) {
			clientesPessoasFisicasDTO.add(toClientePessoaFisicaDTO(cliente));
		}

		return clientesPessoasFisicasDTO;
	}
	
	public static List<ClientePessoaFisicaDTO> toClientePessoaFisicaDTO(List<ClientePessoaFisicaEntity> lisClientePessoaFisica) {

		List<ClientePessoaFisicaDTO> clientesPessoasFisicasDTO = new ArrayList<ClientePessoaFisicaDTO>();

		for (ClientePessoaFisicaEntity cliente : lisClientePessoaFisica) {
			clientesPessoasFisicasDTO.add(toClientePessoaFisicaDTO(cliente));
		}

		return clientesPessoasFisicasDTO;
	}

	public static ClientePessoaFisicaDTO toClientePessoaFisicaDTO(ClientePessoaFisicaEntity clienteEntity) {

		if (clienteEntity == null) {
			return null;
		}
		
		PessoaFisicaDTO pessoaFisicaDTO = PessoaFisicaFactory.toPessoaFisicaDTO(clienteEntity.getPessoaFisica());

		return ClientePessoaFisicaDTO.builder()
				.id(clienteEntity.getId())
				.pessoaFisica(pessoaFisicaDTO)
				.informacaoComplementar(clienteEntity.getInformacaoComplementar())
				.dataAtualizacao(clienteEntity.getDataAtualizacao())
				.situacaoAtivo(clienteEntity.isSituacaoAtivo())
				.build();
	}

	public static Set<ClientePessoaFisicaEntity> toClientePessoaFisicaEntity(Set<ClientePessoaFisicaDTO> listClientePessoaFisicaDTO) {

		Set<ClientePessoaFisicaEntity> clientesPessoasFisicasEntity = new HashSet<ClientePessoaFisicaEntity>();

		for (ClientePessoaFisicaDTO clienteDTO : listClientePessoaFisicaDTO) {
			clientesPessoasFisicasEntity.add(toClientePessoaFisicaEntity(clienteDTO));
		}

		return clientesPessoasFisicasEntity;
	}

	public static ClientePessoaFisicaEntity toClientePessoaFisicaEntity(ClientePessoaFisicaDTO clienteDTO) {

		if (clienteDTO == null) {
			return null;
		}
		
		PessoaFisicaEntity pessoaFisicaEntity = PessoaFisicaFactory.toPessoaFisicaEntity(clienteDTO.getPessoaFisica());
		
		return ClientePessoaFisicaEntity.builder()
				.id(clienteDTO.getId())
				.pessoaFisica(pessoaFisicaEntity)
				.informacaoComplementar(clienteDTO.getInformacaoComplementar())
				.dataAtualizacao(clienteDTO.getDataAtualizacao())
				.situacaoAtivo(clienteDTO.isSituacaoAtivo())
				.build();
	}

	public static ClientePessoaFisicaDTO toClientePessoaFisicaDTO(RequestClientePessoaFisicaDTO requestClientePessoaFisicaDTO) {
		
		if (requestClientePessoaFisicaDTO == null) {
			return null;
		}
		
		PessoaFisicaDTO pessoaFisicaDTO = PessoaFisicaFactory.toPessoaFisicaDTO(requestClientePessoaFisicaDTO.getPessoaFisica());

		return ClientePessoaFisicaDTO.builder()
				.pessoaFisica(pessoaFisicaDTO)
				.informacaoComplementar(requestClientePessoaFisicaDTO.getInformacaoComplementar())
				.situacaoAtivo(requestClientePessoaFisicaDTO.isSituacaoAtivo())
				.build();
		
	}


}
