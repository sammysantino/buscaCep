package br.com.labs.buscacep.rest;

import lombok.Getter;

public enum ECodigoRetorno {
	SUCESSO(1, "Sucesso"),
	VALIDACAO(2, "Dados Inválidos"),
	ERRO(3, "Erro");
	
	@Getter private Integer codigo;
	@Getter private String descricao;
	
	ECodigoRetorno(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
