package com.br.builders.api.factory;

import java.util.HashSet;
import java.util.Set;

import com.br.builders.api.dto.RequestTelefoneDTO;
import com.br.builders.api.dto.TelefoneDTO;
import com.br.builders.api.dto.TipoTelefoneDTO;
import com.br.builders.api.entity.TelefoneEntity;
import com.br.builders.api.entity.TipoTelefoneEntity;

public class TelefoneFactory {

	public static Set<TelefoneDTO> toTelefoneDTO(Set<TelefoneEntity> lisTelefone) {

		Set<TelefoneDTO> lisTelefoneDTO = new HashSet<TelefoneDTO>();

		for (TelefoneEntity telefone : lisTelefone) {
			lisTelefoneDTO.add(toTelefoneDTO(telefone));
		}

		return lisTelefoneDTO;
	}

	public static TelefoneDTO toTelefoneDTO(TelefoneEntity telefoneEntity) {

		if (telefoneEntity == null) {
			return null;
		}
		
		TipoTelefoneDTO tipoTelefoneDTO = TipoTelefoneFactory.toTipoTelefoneDTO(telefoneEntity.getTipoTelefone());

		return TelefoneDTO.builder()
				.id(telefoneEntity.getId())
				.telefone(telefoneEntity.getTelefone())
				.ddd(telefoneEntity.getDdd())
				.ddi(telefoneEntity.getDdi())
				.tipoTelefone(tipoTelefoneDTO)
				.build();
	}

	public static Set<TelefoneEntity> toTelefoneEntity(Set<TelefoneDTO> listTelefoneDTO) {

		Set<TelefoneEntity> listTelefoneEntity = new HashSet<TelefoneEntity>();

		for (TelefoneDTO telefoneDTO : listTelefoneDTO) {
			listTelefoneEntity.add(toTelefoneEntity(telefoneDTO));
		}

		return listTelefoneEntity;
	}

	public static TelefoneEntity toTelefoneEntity(TelefoneDTO telefoneDTO) {

		if (telefoneDTO == null) {
			return null;
		}
		
		TipoTelefoneEntity tipoTelefoneEntity = TipoTelefoneFactory.toTipoTelefoneEntity(telefoneDTO.getTipoTelefone());

		return TelefoneEntity.builder()
				.id(telefoneDTO.getId())
				.telefone(telefoneDTO.getTelefone())
				.ddd(telefoneDTO.getDdd())
				.ddi(telefoneDTO.getDdi())
				.tipoTelefone(tipoTelefoneEntity)
				.build();
	}

	public static Set<TelefoneDTO> requestToTelefoneDTO(Set<RequestTelefoneDTO> telefones) {
		Set<TelefoneDTO> lisTelefoneDTO = new HashSet<TelefoneDTO>();

		for (RequestTelefoneDTO telefone : telefones) {
			lisTelefoneDTO.add(toTelefoneDTO(telefone));
		}

		return lisTelefoneDTO;
	}

	private static TelefoneDTO toTelefoneDTO(RequestTelefoneDTO telefone) {
		if (telefone == null) {
			return null;
		}
		
		TipoTelefoneDTO tipoTelefoneDTO = TipoTelefoneFactory.toTipoTelefoneDTO(telefone.getTipoTelefone());

		return TelefoneDTO.builder()
				.telefone(telefone.getTelefone())
				.ddd(telefone.getDdd())
				.ddi(telefone.getDdi())
				.tipoTelefone(tipoTelefoneDTO)
				.build();
	}

}
