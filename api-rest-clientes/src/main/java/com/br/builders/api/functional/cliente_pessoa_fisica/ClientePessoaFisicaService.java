package com.br.builders.api.functional.cliente_pessoa_fisica;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.builders.api.dto.ClientePessoaFisicaDTO;
import com.br.builders.api.dto.FiltroClientePessoaFisicaDTO;
import com.br.builders.api.dto.PaginaConsultaDTO;
import com.br.builders.api.dto.RequestClientePessoaFisicaDTO;
import com.br.builders.api.entity.ClientePessoaFisicaEntity;
import com.br.builders.api.entity.PessoaFisicaEntity;
import com.br.builders.api.exception.BusinessException;
import com.br.builders.api.factory.ClientePessoaFisicaFactory;
import com.br.builders.api.functional.pessoa_fisica.PessoaFisicaService;

@Service
public class ClientePessoaFisicaService {
	
	@Autowired
	private ClientePessoaFisicaRepository clientePessoaFisicaRepository;
	
	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	public PaginaConsultaDTO<ClientePessoaFisicaDTO> pesquisarClientes(FiltroClientePessoaFisicaDTO filtro) throws BusinessException {
		
		List<ClientePessoaFisicaDTO> listResultado = ClientePessoaFisicaFactory.toClientePessoaFisicaDTO(this.clientePessoaFisicaRepository.pesquisarCliente(filtro));
		
		PaginaConsultaDTO<ClientePessoaFisicaDTO> pagina = new PaginaConsultaDTO<ClientePessoaFisicaDTO>().pagina(filtro.getPagina())
				.registros(filtro.getRegistros()).paginas(filtro.getPaginas()).lista(listResultado);
		pagina.setRegistrosPorPagina(filtro.getRegistrosPorPagina());
		
		return pagina;
	}

	public ClientePessoaFisicaDTO salvarCliente(ClientePessoaFisicaDTO clientePessoaFisicaDTO) {
		PessoaFisicaEntity pessoaFisicaEntity = pessoaFisicaService.salvar(clientePessoaFisicaDTO.getPessoaFisica());
		
		ClientePessoaFisicaEntity clientePessoaFisicaEntity = ClientePessoaFisicaFactory.toClientePessoaFisicaEntity(clientePessoaFisicaDTO);
		clientePessoaFisicaEntity.setPessoaFisica(pessoaFisicaEntity);
		clientePessoaFisicaEntity.setDataAtualizacao(LocalDateTime.now());
		
		return ClientePessoaFisicaFactory.toClientePessoaFisicaDTO(salvar(clientePessoaFisicaEntity));
	}
	
	public ClientePessoaFisicaDTO salvarCliente(RequestClientePessoaFisicaDTO requestClientePessoaFisicaDTO) {
		if (requestClientePessoaFisicaDTO.getPessoaFisica() == null) {
			throw new BusinessException("Dados da Pessoa Física é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (requestClientePessoaFisicaDTO.getPessoaFisica().getCpf() != null) {
			ClientePessoaFisicaDTO dto = buscarClientePorCPF(requestClientePessoaFisicaDTO.getPessoaFisica().getCpf());
			if (dto != null) {
				throw new BusinessException("Cliente já cadastrado com este CPF.", HttpStatus.BAD_REQUEST);	
			}
		}
		
		ClientePessoaFisicaDTO clientePessoaFisicaDTO = ClientePessoaFisicaFactory.toClientePessoaFisicaDTO(requestClientePessoaFisicaDTO);
		return salvarCliente(clientePessoaFisicaDTO);
	}

	public void excluirCliente(ClientePessoaFisicaDTO clientePessoaFisicaDTO) {
		ClientePessoaFisicaEntity clientePessoaFisicaEntity = this.clientePessoaFisicaRepository.buscarClientePorCPF(clientePessoaFisicaDTO.getPessoaFisica().getCpf());
		excluir(clientePessoaFisicaEntity);
	}
	
	public ClientePessoaFisicaDTO buscarClientePorCPF(String cpf) {
		
		if (cpf == null) {
			throw new BusinessException("CPF é obrigatório", HttpStatus.BAD_REQUEST);
		}else if (cpf.length() < 11 ||cpf.length() > 11) {
			throw new BusinessException("CPF inválido", HttpStatus.BAD_REQUEST);
		}
		
		ClientePessoaFisicaEntity clientePessoaFisicaEntity = this.clientePessoaFisicaRepository.buscarClientePorCPF(cpf);
		return ClientePessoaFisicaFactory.toClientePessoaFisicaDTO(clientePessoaFisicaEntity);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ClientePessoaFisicaEntity salvar(ClientePessoaFisicaEntity clientePessoaFisicaEntity) {
		return this.clientePessoaFisicaRepository.save(clientePessoaFisicaEntity);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(ClientePessoaFisicaEntity clientePessoaFisicaEntity) {
		this.clientePessoaFisicaRepository.delete(clientePessoaFisicaEntity);
	}
	
}
