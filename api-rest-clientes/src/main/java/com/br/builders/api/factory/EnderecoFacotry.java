package com.br.builders.api.factory;

import java.util.HashSet;
import java.util.Set;

import com.br.builders.api.dto.CidadeDTO;
import com.br.builders.api.dto.EnderecoDTO;
import com.br.builders.api.dto.RequestEnderecoDTO;
import com.br.builders.api.entity.CidadeEntity;
import com.br.builders.api.entity.EnderecoEntity;

public class EnderecoFacotry {

	public static Set<EnderecoDTO> toEnderecoDTO(Set<EnderecoEntity> lisEndereco) {

		Set<EnderecoDTO> enderecosDTO = new HashSet<EnderecoDTO>();

		for (EnderecoEntity endereco : lisEndereco) {
			enderecosDTO.add(toEnderecoDTO(endereco));
		}

		return enderecosDTO;
	}

	public static EnderecoDTO toEnderecoDTO(EnderecoEntity enderecoEntity) {

		if (enderecoEntity == null) {
			return null;
		}
		
		CidadeDTO cidadeDTO = CidadeFactory.toCidadeDTO(enderecoEntity.getCidade());

		return EnderecoDTO.builder()
				.id(enderecoEntity.getId())
				.bairro(enderecoEntity.getBairro())
				.cep(enderecoEntity.getCep())
				.cidade(cidadeDTO)
				.logradouro(enderecoEntity.getLogradouro())
				.numero(enderecoEntity.getNumero())
				.rua(enderecoEntity.getRua())
				.complemento(enderecoEntity.getComplemento())
				.build();
	}

	public static Set<EnderecoEntity> toEnderecoEntity(Set<EnderecoDTO> listEnderecoDTO) {

		Set<EnderecoEntity> enderecosEntity = new HashSet<EnderecoEntity>();

		for (EnderecoDTO enderecoDTO : listEnderecoDTO) {
			enderecosEntity.add(toEnderecoEntity(enderecoDTO));
		}

		return enderecosEntity;
	}

	public static EnderecoEntity toEnderecoEntity(EnderecoDTO enderecoDTO) {

		if (enderecoDTO == null) {
			return null;
		}
		
		CidadeEntity cidadeEntity = CidadeFactory.toCidadeEntity(enderecoDTO.getCidade());

		return EnderecoEntity.builder()
				.id(enderecoDTO.getId())
				.bairro(enderecoDTO.getBairro())
				.cep(enderecoDTO.getCep())
				.cidade(cidadeEntity)
				.logradouro(enderecoDTO.getLogradouro())
				.numero(enderecoDTO.getNumero())
				.rua(enderecoDTO.getRua())
				.complemento(enderecoDTO.getComplemento())
				.build();
	}

	public static EnderecoDTO toEnderecoDTO(RequestEnderecoDTO endereco) {
		if (endereco == null) {
			return null;
		}
		
		CidadeDTO cidadeDTO = CidadeFactory.toCidadeDTO(endereco.getCidade());

		return EnderecoDTO.builder()
				.bairro(endereco.getBairro())
				.cep(endereco.getCep())
				.cidade(cidadeDTO)
				.logradouro(endereco.getLogradouro())
				.numero(endereco.getNumero())
				.rua(endereco.getRua())
				.complemento(endereco.getComplemento())
				.build();
	}

}
