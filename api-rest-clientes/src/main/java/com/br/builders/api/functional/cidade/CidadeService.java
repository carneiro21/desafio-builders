package com.br.builders.api.functional.cidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.br.builders.api.dto.CidadeDTO;
import com.br.builders.api.entity.CidadeEntity;
import com.br.builders.api.entity.UfEntity;
import com.br.builders.api.exception.BusinessException;
import com.br.builders.api.factory.CidadeFactory;
import com.br.builders.api.functional.uf.UfService;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private UfService ufService;

	public CidadeEntity salvar(CidadeEntity cidadeEntity) {
		return cidadeRepository.save(cidadeEntity);
	}
	
	public CidadeEntity salvar(CidadeDTO cidadeDTO) {
		if (cidadeDTO.getNomeCidade() == null) {
			throw new BusinessException("Nome da ciade é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (cidadeDTO.getUf() == null) {
			throw new BusinessException("UF é obrigatório", HttpStatus.BAD_REQUEST);
		}
		
		CidadeEntity cidadeEntity = buscarCidadePorNome(cidadeDTO.getNomeCidade());
		if (cidadeEntity != null) {
			cidadeDTO.setId(cidadeEntity.getId());
		}
		
		UfEntity ufEntity = ufService.salvar(cidadeDTO.getUf());
		cidadeEntity = CidadeFactory.toCidadeEntity(cidadeDTO);
		cidadeEntity.setUf(ufEntity);
		return salvar(cidadeEntity);
	}
	
	public CidadeEntity buscarCidadePorNome(String nomeCidade) {
		return this.cidadeRepository.findByNomeCidade(nomeCidade);
	}
}
