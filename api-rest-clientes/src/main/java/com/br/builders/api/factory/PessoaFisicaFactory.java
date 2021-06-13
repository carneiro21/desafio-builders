package com.br.builders.api.factory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.br.builders.api.dto.EnderecoDTO;
import com.br.builders.api.dto.PessoaFisicaDTO;
import com.br.builders.api.dto.RequestPessoaFisicaDTO;
import com.br.builders.api.dto.TelefoneDTO;
import com.br.builders.api.entity.EnderecoEntity;
import com.br.builders.api.entity.PessoaFisicaEntity;
import com.br.builders.api.entity.TelefoneEntity;
import com.br.builders.api.entity.TelefonePessoaFisicaEntity;

public class PessoaFisicaFactory {

	public static List<PessoaFisicaDTO> toPessoaFisicaDTO(List<PessoaFisicaEntity> lisPessoaFisica) {

		List<PessoaFisicaDTO> lisPessoaFisicaDTO = new ArrayList<PessoaFisicaDTO>();

		for (PessoaFisicaEntity pessoaFisica : lisPessoaFisica) {
			lisPessoaFisicaDTO.add(toPessoaFisicaDTO(pessoaFisica));
		}

		return lisPessoaFisicaDTO;
	}

	public static PessoaFisicaDTO toPessoaFisicaDTO(PessoaFisicaEntity pessoaFisicaEntity) {

		if (pessoaFisicaEntity == null) {
			return null;
		}
		
		EnderecoDTO endereco = EnderecoFacotry.toEnderecoDTO(pessoaFisicaEntity.getEndereco());
		
		Set<TelefoneDTO> telefones = null;
		if (pessoaFisicaEntity.getTelefones() != null) {
			Set<TelefoneEntity> telefonesEntity = pessoaFisicaEntity.getTelefones().stream().map(t -> t.getTelefone())
					.collect(Collectors.toSet());
			telefones = TelefoneFactory.toTelefoneDTO(telefonesEntity);
		}

		return PessoaFisicaDTO.builder()
				.id(pessoaFisicaEntity.getId())
				.nome(pessoaFisicaEntity.getNome())
				.telefones(telefones)
				.endereco(endereco)
				.cpf(pessoaFisicaEntity.getCpf())
				.email(pessoaFisicaEntity.getEmail())
				.dataNascimento(pessoaFisicaEntity.getDataNascimento())
				.build();
	}

	public static List<PessoaFisicaEntity> toPessoaFisicaEntity(List<PessoaFisicaDTO> listPessoaFisicaDTO) {

		List<PessoaFisicaEntity> listPessoaFisicaEntity = new ArrayList<PessoaFisicaEntity>();

		for (PessoaFisicaDTO pessoaFisicaDTO : listPessoaFisicaDTO) {
			listPessoaFisicaEntity.add(toPessoaFisicaEntity(pessoaFisicaDTO));
		}

		return listPessoaFisicaEntity;
	}

	public static PessoaFisicaEntity toPessoaFisicaEntity(PessoaFisicaDTO pessoaFisicaDTO) {

		if (pessoaFisicaDTO == null) {
			return null;
		}
		
		EnderecoEntity enderecoEntity = EnderecoFacotry.toEnderecoEntity(pessoaFisicaDTO.getEndereco());
		Set<TelefoneEntity> telefones = null;
		Set<TelefonePessoaFisicaEntity> telefonesPessoaFisica = null;
		
		if (pessoaFisicaDTO.getTelefones() != null) {
			telefonesPessoaFisica = new  HashSet<TelefonePessoaFisicaEntity>();
			telefones = TelefoneFactory.toTelefoneEntity(pessoaFisicaDTO.getTelefones());
			
			for (TelefoneEntity telefone : telefones) {
				telefonesPessoaFisica.add(TelefonePessoaFisicaEntity.builder().telefone(telefone)
						.dataAtualizacao(LocalDate.now()).build());
			}
		}

		return PessoaFisicaEntity.builder()
				.id(pessoaFisicaDTO.getId())
				.nome(pessoaFisicaDTO.getNome())
				.telefones(telefonesPessoaFisica)
				.endereco(enderecoEntity)
				.cpf(pessoaFisicaDTO.getCpf())
				.email(pessoaFisicaDTO.getEmail())
				.dataNascimento(pessoaFisicaDTO.getDataNascimento())
				.build();
	}

	public static PessoaFisicaDTO toPessoaFisicaDTO(RequestPessoaFisicaDTO pessoaFisica) {
		if (pessoaFisica == null) {
			return null;
		}
		
		EnderecoDTO endereco = EnderecoFacotry.toEnderecoDTO(pessoaFisica.getEndereco());
		
		Set<TelefoneDTO> telefones = TelefoneFactory.requestToTelefoneDTO(pessoaFisica.getTelefones());

		return PessoaFisicaDTO.builder()
				.nome(pessoaFisica.getNome())
				.telefones(telefones)
				.endereco(endereco)
				.cpf(pessoaFisica.getCpf())
				.email(pessoaFisica.getEmail())
				.dataNascimento(pessoaFisica.getDataNascimento())
				.build();
	}

}
