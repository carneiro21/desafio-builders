package com.br.builders.api.factory;

import java.util.ArrayList;
import java.util.List;

import com.br.builders.api.dto.RequestUfDTO;
import com.br.builders.api.dto.UfDTO;
import com.br.builders.api.entity.UfEntity;

public class UfFactory {

	public static List<UfDTO> toUfDTO(List<UfEntity> lisUf) {

		List<UfDTO> lisUfDTO = new ArrayList<UfDTO>();

		for (UfEntity uf : lisUf) {
			lisUfDTO.add(toUfDTO(uf));
		}

		return lisUfDTO;
	}

	public static UfDTO toUfDTO(UfEntity ufEntity) {

		if (ufEntity == null) {
			return null;
		}

		return UfDTO.builder()
				.id(ufEntity.getId())
				.nomeUf(ufEntity.getNomeUf())
				.build();
	}

	public static List<UfEntity> toUfEntity(List<UfDTO> listUfDTO) {

		List<UfEntity> listUfEntity = new ArrayList<UfEntity>();

		for (UfDTO ufDTO : listUfDTO) {
			listUfEntity.add(toUfEntity(ufDTO));
		}

		return listUfEntity;
	}

	public static UfEntity toUfEntity(UfDTO ufDTO) {

		if (ufDTO == null) {
			return null;
		}

		return UfEntity.builder()
				.id(ufDTO.getId())
				.nomeUf(ufDTO.getNomeUf())
				.build();
	}

	public static UfDTO toUfDTO(RequestUfDTO uf) {
		if (uf == null) {
			return null;
		}

		return UfDTO.builder()
				.nomeUf(uf.getNomeUf())
				.build();
	}

}
