package com.br.builders.api.functional.telefone;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.br.builders.api.dto.TelefoneDTO;
import com.br.builders.api.entity.TelefoneEntity;
import com.br.builders.api.exception.BusinessException;
import com.br.builders.api.factory.TelefoneFactory;
import com.br.builders.api.functional.tipo_telefone.TipoTelefoneService;


@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Autowired
	private TipoTelefoneService tipoTelefoneService;
	
	
	public TelefoneEntity salvar(TelefoneEntity telefoneEntity) {
		
		if (telefoneEntity.getTelefone() == null) {
			throw new BusinessException("Número do Telefone é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (telefoneEntity.getDdd() == null) {
			throw new BusinessException("DDD é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (telefoneEntity.getDdi() == null) {
			throw new BusinessException("DDI é obrigatório", HttpStatus.BAD_REQUEST);
		}
		
		telefoneEntity = buscarTelefone(telefoneEntity);
		return this.telefoneRepository.save(telefoneEntity);
	}

	private TelefoneEntity buscarTelefone(TelefoneEntity telefoneEntity) {
		TelefoneEntity telefone = this.telefoneRepository.findByTelefoneAndDddAndDdi(telefoneEntity.getTelefone(), telefoneEntity.getDdd(), telefoneEntity.getDdi());
		if (telefone != null) {
			telefoneEntity.setId(telefone.getId());
		}
		
		return telefoneEntity;
	}
	
	public List<TelefoneEntity> salvar(Set<TelefoneEntity> telefonesEntity) {
		for (TelefoneEntity telefoneEntity : telefonesEntity) {
			telefoneEntity.setTipoTelefone(tipoTelefoneService.salvar(telefoneEntity.getTipoTelefone()));
			telefoneEntity = buscarTelefone(telefoneEntity);
		}
		return this.telefoneRepository.saveAll(telefonesEntity);
	}
	
	public TelefoneEntity salvar(TelefoneDTO telefoneDTO) {
		TelefoneEntity telefoneEntity = TelefoneFactory.toTelefoneEntity(telefoneDTO);
		return this.telefoneRepository.save(telefoneEntity);
	}
	
	public List<TelefoneEntity> salvarTelefones(Set<TelefoneDTO> listTelefoneDTO) {
		Set<TelefoneEntity> telefones = TelefoneFactory.toTelefoneEntity(listTelefoneDTO);
		return salvar(telefones);
	}
}
