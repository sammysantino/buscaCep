package br.com.labs.buscacep.rest;

import lombok.Getter;

public enum ECodigoRetorno {
	ERRO("Erro"),
	SUCESSO("Sucesso");
	
	@Getter private String descricao;
	
	ECodigoRetorno(String descricao) {
		this.descricao = descricao;
	}
}
