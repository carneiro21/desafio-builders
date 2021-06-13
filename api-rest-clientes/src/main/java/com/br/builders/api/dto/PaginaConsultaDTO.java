package com.br.builders.api.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PaginaConsultaDTO<T> {

	protected Integer pagina;
	
    private String orderBy;

	protected Long registros;
	protected Integer paginas;

	protected Integer registrosPorPagina;
	
	@Setter @Getter
	private List<T> lista = new ArrayList<>();

	public Integer getPagina() {
		if (pagina == null) {
			return 0;
		}
		return pagina;
	}
	
	public Integer getRegistrosPorPagina() {
		if (registrosPorPagina == null) {
			return 10;
		}
		return registrosPorPagina;
	}

	public void setRegistros(Long registros) {
		this.registros = registros;
		if (this.registros != null) {
			this.paginas = (int) (registros + getRegistrosPorPagina() - 1) / getRegistrosPorPagina();
		}
	}

	public PaginaConsultaDTO<T> pagina(Integer pagina) {
		this.pagina = pagina;
		return this;
	}

	public PaginaConsultaDTO<T> lista(List<T> lista) {
		this.lista = lista;
		return this;
	}

	public PaginaConsultaDTO<T> paginas(Integer paginas) {
		this.paginas = paginas;
		return this;
	}

	public PaginaConsultaDTO<T> registros(Long registros) {
		this.registros = registros;
		return this;
	}

}
