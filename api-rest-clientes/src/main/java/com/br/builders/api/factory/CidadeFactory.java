package com.br.builders.api.factory;

import java.util.HashSet;
import java.util.Set;

import com.br.builders.api.dto.CidadeDTO;
import com.br.builders.api.dto.RequestCidadeDTO;
import com.br.builders.api.dto.UfDTO;
import com.br.builders.api.entity.CidadeEntity;
import com.br.builders.api.entity.UfEntity;

public class CidadeFactory {

	public static Set<CidadeDTO> toCidadeDTO(Set<CidadeEntity> lisCidade) {

		Set<CidadeDTO> cidadesDTO = new HashSet<CidadeDTO>();

		for (CidadeEntity cidade : lisCidade) {
			cidadesDTO.add(toCidadeDTO(cidade));
		}

		return cidadesDTO;
	}

	public static CidadeDTO toCidadeDTO(CidadeEntity cidadeEntity) {

		if (cidadeEntity == null) {
			return null;
		}
		
		UfDTO ufDTO = UfFactory.toUfDTO(cidadeEntity.getUf());

		return CidadeDTO.builder()
				.id(cidadeEntity.getId())
				.nomeCidade(cidadeEntity.getNomeCidade())
				.uf(ufDTO)
				.build();
	}

	public static Set<CidadeEntity> toCidadeEntity(Set<CidadeDTO> listCidadeDTO) {

		Set<CidadeEntity> listCidadeEntity = new HashSet<CidadeEntity>();

		for (CidadeDTO cidadeDTO : listCidadeDTO) {
			listCidadeEntity.add(toCidadeEntity(cidadeDTO));
		}

		return listCidadeEntity;
	}

	public static CidadeEntity toCidadeEntity(CidadeDTO cidadeDTO) {

		if (cidadeDTO == null) {
			return null;
		}
		
		UfEntity ufEntity = UfFactory.toUfEntity(cidadeDTO.getUf());

		return CidadeEntity.builder()
				.id(cidadeDTO.getId())
				.nomeCidade(cidadeDTO.getNomeCidade())
				.uf(ufEntity)
				.build();
	}

	public static CidadeDTO toCidadeDTO(RequestCidadeDTO cidade) {
		if (cidade == null) {
			return null;
		}
		
		UfDTO ufDTO = UfFactory.toUfDTO(cidade.getUf());

		return CidadeDTO.builder()
				.nomeCidade(cidade.getNomeCidade())
				.uf(ufDTO)
				.build();
	}


}
