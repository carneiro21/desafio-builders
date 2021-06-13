package com.br.builders.api.factory;

import java.util.ArrayList;
import java.util.List;

import com.br.builders.api.dto.RequestTipoTelefoneDTO;
import com.br.builders.api.dto.TipoTelefoneDTO;
import com.br.builders.api.entity.TipoTelefoneEntity;

public class TipoTelefoneFactory {

	public static List<TipoTelefoneDTO> toTipoTelefoneDTO(List<TipoTelefoneEntity> lisTipoTelefone) {

		List<TipoTelefoneDTO> lisTipoTelefoneDTO = new ArrayList<TipoTelefoneDTO>();

		for (TipoTelefoneEntity tipoTelefone : lisTipoTelefone) {
			lisTipoTelefoneDTO.add(toTipoTelefoneDTO(tipoTelefone));
		}

		return lisTipoTelefoneDTO;
	}

	public static TipoTelefoneDTO toTipoTelefoneDTO(TipoTelefoneEntity tipoTelefoneEntity) {

		if (tipoTelefoneEntity == null) {
			return null;
		}

		return TipoTelefoneDTO.builder()
				.id(tipoTelefoneEntity.getId())
				.nomeTipoTelefone(tipoTelefoneEntity.getNomeTipoTelefone())
				.build();
	}

	public static List<TipoTelefoneEntity> toTipoTelefoneEntity(List<TipoTelefoneDTO> listTipoTelefoneDTO) {

		List<TipoTelefoneEntity> listTipoTelefoneEntity = new ArrayList<TipoTelefoneEntity>();

		for (TipoTelefoneDTO tipoTelefoneDTO : listTipoTelefoneDTO) {
			listTipoTelefoneEntity.add(toTipoTelefoneEntity(tipoTelefoneDTO));
		}

		return listTipoTelefoneEntity;
	}

	public static TipoTelefoneEntity toTipoTelefoneEntity(TipoTelefoneDTO tipoTelefoneDTO) {

		if (tipoTelefoneDTO == null) {
			return null;
		}
		
		return TipoTelefoneEntity.builder()
				.id(tipoTelefoneDTO.getId())
				.nomeTipoTelefone(tipoTelefoneDTO.getNomeTipoTelefone())
				.build();
	}

	public static TipoTelefoneDTO toTipoTelefoneDTO(RequestTipoTelefoneDTO tipoTelefone) {
		if (tipoTelefone == null) {
			return null;
		}

		return TipoTelefoneDTO.builder()
				.nomeTipoTelefone(tipoTelefone.getNomeTipoTelefone())
				.build();
	}

}
