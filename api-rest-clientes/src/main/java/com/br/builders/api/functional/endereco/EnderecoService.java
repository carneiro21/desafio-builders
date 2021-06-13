package com.br.builders.api.functional.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.br.builders.api.dto.EnderecoDTO;
import com.br.builders.api.entity.CidadeEntity;
import com.br.builders.api.entity.EnderecoEntity;
import com.br.builders.api.exception.BusinessException;
import com.br.builders.api.factory.EnderecoFacotry;
import com.br.builders.api.functional.cidade.CidadeService;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	public EnderecoEntity salvar(EnderecoEntity enderecoEntity) {
		return this.enderecoRepository.save(enderecoEntity);
	}
	
	public EnderecoEntity salvar(EnderecoDTO enderecoDTO) {
		if (enderecoDTO.getCep() == null) {
			throw new BusinessException("CEP é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (enderecoDTO.getCidade() == null) {
			throw new BusinessException("Cidade é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (enderecoDTO.getRua() == null) {
			throw new BusinessException("Rua é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (enderecoDTO.getBairro() == null) {
			throw new BusinessException("Bairro é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (enderecoDTO.getNumero() == null) {
			throw new BusinessException("O campo Número é obrigatório", HttpStatus.BAD_REQUEST);
		}
		
		CidadeEntity cidadeEntity = cidadeService.salvar(enderecoDTO.getCidade());
		
		EnderecoEntity enderecoEntity = this.enderecoRepository.findByCep(enderecoDTO.getCep());
		if (enderecoEntity != null) {
			enderecoDTO.setId(enderecoEntity.getId());
		}
		
		enderecoEntity = EnderecoFacotry.toEnderecoEntity(enderecoDTO);
		enderecoEntity.setCidade(cidadeEntity);
		return salvar(enderecoEntity);
	}
	
}
