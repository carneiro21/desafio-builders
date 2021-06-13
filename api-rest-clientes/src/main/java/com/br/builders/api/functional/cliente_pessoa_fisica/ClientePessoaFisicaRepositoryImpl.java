package com.br.builders.api.functional.cliente_pessoa_fisica;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.builders.api.dto.FiltroClientePessoaFisicaDTO;
import com.br.builders.api.entity.ClientePessoaFisicaEntity;
import com.br.builders.util.DateUtil;

public class ClientePessoaFisicaRepositoryImpl implements ClientePessoaFisicaRepositoryCustom {
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<ClientePessoaFisicaEntity> pesquisarCliente(FiltroClientePessoaFisicaDTO filtro) {

		StringBuilder sql = new StringBuilder();

		String orderBy = "cliente.dataAtualizacao desc";
		if (filtro.getOrderBy() != null) {
			orderBy = filtro.getOrderBy();
		}
		
		StringBuilder sqlFrom = new StringBuilder();
		sqlFrom.append("select cliente from ClientePessoaFisicaEntity cliente ");
		sqlFrom.append("join cliente.pessoaFisica pessoaFisica ");
		sqlFrom.append("join pessoaFisica.telefones telefoneCliente ");
		sqlFrom.append("join pessoaFisica.endereco enderecoCliente ");
		
		sql.append("where (:cpf is null or pessoaFisica.cpf like :cpf) ");
		sql.append("and (:nome is null or pessoaFisica.nome like :nome) ");
		sql.append("and (:email is null or pessoaFisica.email like :email) ");
		sql.append("and (:ddi is null or telefoneCliente.telefone.ddi like :ddi) ");
		sql.append("and (:ddd is null or telefoneCliente.telefone.ddd like :ddd) ");
		sql.append("and (:telefone is null or telefoneCliente.telefone.telefone like :telefone) ");
		sql.append("and (:cep is null or enderecoCliente.cep = :cep) ");
		sql.append("and (:dataNascimentoInicio is null or (pessoaFisica.dataNascimento between :dataNascimentoInicio and :dataNascimentoFim)) ");
		
		if (filtro.getPagina() == 0) {
			filtro.setPagina(1);
			filtro.setRegistros(count(sql.toString(), filtro));
		}
		
		TypedQuery<ClientePessoaFisicaEntity> query = manager.createQuery(sqlFrom.toString() + sql.append("order by ").append(orderBy), ClientePessoaFisicaEntity.class);
		parameters(query, filtro);
		query.setMaxResults(filtro.getRegistrosPorPagina());
		query.setFirstResult((filtro.getPagina() - 1) * filtro.getRegistrosPorPagina());
		return query.getResultList();
		
	}
	
	public Long count(String sql, FiltroClientePessoaFisicaDTO filtroClientePessoaFisicaDTO) {
		
		StringBuilder sqlFrom = new StringBuilder();
		sqlFrom.append("select COUNT(cliente.id) from ClientePessoaFisicaEntity cliente ");
		sqlFrom.append("join cliente.pessoaFisica pessoaFisica ");
		sqlFrom.append("join pessoaFisica.telefones telefoneCliente ");
		sqlFrom.append("join pessoaFisica.endereco enderecoCliente ");
		sqlFrom.append(sql);
		
		Query query = manager.createQuery(sqlFrom.toString());
		parameters(query, filtroClientePessoaFisicaDTO);
		return (Long) query.getSingleResult();
	}
	
	private void parameters(Query query, FiltroClientePessoaFisicaDTO filtro) {
		
		query.setParameter("cep", filtro.getCep());
		query.setParameter("cpf", filtro.getCpf());
		query.setParameter("nome",(filtro.getNome() != null ? (filtro.getNome().toUpperCase()+"%") : filtro.getNome()));
		query.setParameter("email", filtro.getEmail());
		query.setParameter("ddi", filtro.getDdi());
		query.setParameter("ddd", filtro.getDdd());
		query.setParameter("telefone", filtro.getTelefone());
		query.setParameter("dataNascimentoInicio", DateUtil.transformStringToLocalDate(filtro.getDataNascimentoInicio()));
		query.setParameter("dataNascimentoFim", DateUtil.transformStringToLocalDate(filtro.getDataNascimentoFim()));
	}

	@Override
	public ClientePessoaFisicaEntity buscarClientePorCPF(String cpf) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select cliente from ClientePessoaFisicaEntity cliente ");
			sql.append("join cliente.pessoaFisica pessoaFisica ");
			sql.append("where pessoaFisica.cpf like :cpf ");
			
			TypedQuery<ClientePessoaFisicaEntity> query = manager.createQuery(sql.toString(), ClientePessoaFisicaEntity.class);
			query.setParameter("cpf",cpf);
			
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
