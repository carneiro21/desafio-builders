package com.br.builders.api.functional.pessoa_fisica;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.br.builders.api.dto.PessoaFisicaDTO;
import com.br.builders.api.entity.EnderecoEntity;
import com.br.builders.api.entity.PessoaFisicaEntity;
import com.br.builders.api.entity.TelefoneEntity;
import com.br.builders.api.entity.TelefonePessoaFisicaEntity;
import com.br.builders.api.entity.TelefonePessoaFisicaIdEntity;
import com.br.builders.api.exception.BusinessException;
import com.br.builders.api.factory.PessoaFisicaFactory;
import com.br.builders.api.factory.TelefoneFactory;
import com.br.builders.api.functional.endereco.EnderecoService;
import com.br.builders.api.functional.telefone.TelefoneService;

@Service
public class PessoaFisicaService {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	
	public PessoaFisicaEntity salvar(PessoaFisicaEntity pessoaFisicaEntity) {
		return this.pessoaFisicaRepository.save(pessoaFisicaEntity);
	}
	
	public PessoaFisicaEntity salvar(PessoaFisicaDTO pessoaFisicaDTO) {
		
		if (pessoaFisicaDTO.getCpf() == null) {
			throw new BusinessException("CPF é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (pessoaFisicaDTO.getCpf().length() < 11 ||pessoaFisicaDTO.getCpf().length() > 11) {
			throw new BusinessException("CPF inválido", HttpStatus.BAD_REQUEST);
		}else if (pessoaFisicaDTO.getEmail() == null) {
			throw new BusinessException("Email é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (pessoaFisicaDTO.getDataNascimento() == null) {
			throw new BusinessException("Data de nascimento é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (pessoaFisicaDTO.getEndereco() == null) {
			throw new BusinessException("Endereço é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (pessoaFisicaDTO.getTelefones() == null) {
			throw new BusinessException("Pelo menos um telefone é obrigatório", HttpStatus.BAD_REQUEST);
		}
		
		PessoaFisicaEntity pessoaFisicaEntity = PessoaFisicaFactory.toPessoaFisicaEntity(pessoaFisicaDTO);
		EnderecoEntity enderecoEntity = enderecoService.salvar(pessoaFisicaDTO.getEndereco());
		pessoaFisicaEntity.setEndereco(enderecoEntity);
		
		Set<TelefoneEntity> telefonesEntity = TelefoneFactory.toTelefoneEntity(pessoaFisicaDTO.getTelefones());
		telefoneService.salvar(telefonesEntity);
		
		pessoaFisicaEntity.getTelefones().clear();
		salvar(pessoaFisicaEntity);
		
		for (TelefoneEntity telefone : telefonesEntity) {
			
			TelefonePessoaFisicaIdEntity id = TelefonePessoaFisicaIdEntity.builder()
					.idTelefone(telefone.getId())
					.idPessoaFisica(pessoaFisicaEntity.getId())
					.build();
			
			pessoaFisicaEntity.getTelefones().add(TelefonePessoaFisicaEntity.builder()
					.id(id)
					.dataAtualizacao(LocalDate.now()).build());
		}
		
		return salvar(pessoaFisicaEntity);
	}
	
	
}
