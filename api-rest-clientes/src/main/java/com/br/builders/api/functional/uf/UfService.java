package com.br.builders.api.functional.uf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.br.builders.api.dto.UfDTO;
import com.br.builders.api.entity.UfEntity;
import com.br.builders.api.exception.BusinessException;
import com.br.builders.api.factory.UfFactory;

@Service
public class UfService {

	@Autowired
	private UfRepository ufRepository;
	
	public UfEntity salvar(UfEntity UfEntity) {
		return this.ufRepository.save(UfEntity);
	}
	
	public UfEntity salvar(UfDTO UfDTO) {
		if (UfDTO.getNomeUf() == null) {
			throw new BusinessException("Nome da UF é obrigatório", HttpStatus.BAD_REQUEST);
		}
		UfEntity UfEntity = buscarUF(UfDTO.getNomeUf());
		if (UfEntity == null) {
			UfEntity = UfFactory.toUfEntity(UfDTO);
			return salvar(UfEntity);
		}
		return UfEntity;
	}
	
	public UfEntity buscarUF(String nomeUf) {
		return this.ufRepository.findByNomeUf(nomeUf);
	}
}
