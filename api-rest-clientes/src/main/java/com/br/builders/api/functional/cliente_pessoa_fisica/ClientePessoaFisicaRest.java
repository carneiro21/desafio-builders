package com.br.builders.api.functional.cliente_pessoa_fisica;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.builders.api.dto.ClientePessoaFisicaDTO;
import com.br.builders.api.dto.FiltroClientePessoaFisicaDTO;
import com.br.builders.api.dto.PaginaConsultaDTO;
import com.br.builders.api.dto.RequestClientePessoaFisicaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cliente-pessoa-fisica")
public class ClientePessoaFisicaRest {
	
	private ClientePessoaFisicaService clientePessoaFisicaService;

	@Autowired
	public ClientePessoaFisicaRest(ClientePessoaFisicaService clientePessoaFisicaService) {
		this.clientePessoaFisicaService = clientePessoaFisicaService;
	}

	@GetMapping("/v1/pesquisar-clientes")
	@Operation(summary = "Pesquisa clientes através de seus atributos.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes retornados com sucesso.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientePessoaFisicaDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado.")})
	public ResponseEntity<PaginaConsultaDTO<ClientePessoaFisicaDTO>> pesquisarClientes(final FiltroClientePessoaFisicaDTO filtro) {
		PaginaConsultaDTO<ClientePessoaFisicaDTO> pagina = clientePessoaFisicaService.pesquisarClientes(filtro);
		
		if (pagina.getLista() == null || pagina.getLista().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pagina);
	}
	
	@GetMapping("/{cpf}")
	@Operation(summary = "Busca um cliente pelo CPF.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso.", content = @Content(schema = @Schema(implementation = ClientePessoaFisicaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado.")
     })
	public ResponseEntity<ClientePessoaFisicaDTO> buscarCliente(@PathVariable final String cpf){
		ClientePessoaFisicaDTO cliente = clientePessoaFisicaService.buscarClientePorCPF(cpf);
		
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping
	@Operation(summary = "Cria um cliente do tipo pessoa fisíca.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente pessoa fisíca criado com sucesso.")})
	public ResponseEntity<ClientePessoaFisicaDTO> criarCliente(@RequestBody final RequestClientePessoaFisicaDTO requestClientePessoaFisicaDTO){
		ClientePessoaFisicaDTO cliente = clientePessoaFisicaService.salvarCliente(requestClientePessoaFisicaDTO);
		
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}")
				   .buildAndExpand(cliente.getPessoaFisica().getCpf()).toUri();
		 
		 return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@Operation(summary = "Altera os dados de um cliente do tipo pessoa fisíca.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dados do cliente alterado com sucesso.")})
	public ResponseEntity<ClientePessoaFisicaDTO> atualizarCliente(@RequestBody final ClientePessoaFisicaDTO clientePessoaFisicaDTO){
		clientePessoaFisicaService.salvarCliente(clientePessoaFisicaDTO);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{cpf}")
	@Operation(summary = "Remove do banco de dados um cliente do tipo pessoa fisíca.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente pessoa fisíca excluído com sucesso.")})
	public ResponseEntity<ClientePessoaFisicaDTO> excluirCliente(@PathVariable final String cpf){
		ClientePessoaFisicaDTO cliente = clientePessoaFisicaService.buscarClientePorCPF(cpf);
		
		if (cliente == null) {
			 return ResponseEntity.notFound().build();
		}
		
		clientePessoaFisicaService.excluirCliente(cliente);
		return ResponseEntity.ok().build();
	}
	
	

}
