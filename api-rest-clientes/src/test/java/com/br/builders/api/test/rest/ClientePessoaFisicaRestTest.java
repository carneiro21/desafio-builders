package com.br.builders.api.test.rest;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.br.builders.api.dto.ClientePessoaFisicaDTO;
import com.br.builders.api.functional.cliente_pessoa_fisica.ClientePessoaFisicaRest;
import com.br.builders.api.functional.cliente_pessoa_fisica.ClientePessoaFisicaService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest
public class ClientePessoaFisicaRestTest {
	
	@Autowired
	private ClientePessoaFisicaRest clientePessoaFisicaRest;
	
	@MockBean
	private ClientePessoaFisicaService clientePessoaFisicaService;
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.clientePessoaFisicaRest);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoPesquisarClientePorCPF() {
		
		String cpf = "02680204182";
		
		when(this.clientePessoaFisicaService.buscarClientePorCPF(cpf))
			.thenReturn(ClientePessoaFisicaDTO.builder().dataAtualizacao(LocalDateTime.now()).build());
		
		RestAssuredMockMvc.given().accept(ContentType.JSON)
				.get("/cliente-pessoa-fisica/{cpf}", cpf)
					.then()
					.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarNaoEncontrado_QuandoPesquisarClientePorCPF() {
		
		String cpfNaoEncontrado = "5555555555";
		
		when(this.clientePessoaFisicaService.buscarClientePorCPF(cpfNaoEncontrado))
			.thenReturn(null);
		
		RestAssuredMockMvc.given().accept(ContentType.JSON)
				.get("/cliente-pessoa-fisica/{cpf}", cpfNaoEncontrado)
					.then()
					.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
