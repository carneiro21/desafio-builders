package com.br.builders.api.functional.tipo_telefone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.br.builders.api.entity.TipoTelefoneEntity;
import com.br.builders.api.exception.BusinessException;

@Service
public class TipoTelefoneService {

	@Autowired
	private TipoTelefoneRepository tipoTelefoneRepository;
	
	public TipoTelefoneEntity buscarTipoTelefonePorNome(String nomeTipoTelefone) {
		return this.tipoTelefoneRepository.findByNomeTipoTelefone(nomeTipoTelefone);
	}
	
	public TipoTelefoneEntity salvar(TipoTelefoneEntity tipoTelefone) {
		
		if (tipoTelefone.getNomeTipoTelefone() == null) {
			throw new BusinessException("Número do Tipo de Telefone é obrigatório", HttpStatus.BAD_REQUEST);
		}
		
		TipoTelefoneEntity tipoTelefoneEntity = buscarTipoTelefonePorNome(tipoTelefone.getNomeTipoTelefone());
		if (tipoTelefoneEntity == null) {
			return this.tipoTelefoneRepository.save(tipoTelefone);
		}else {
			return tipoTelefoneEntity;
		}
	}
}
