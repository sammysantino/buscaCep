package br.com.labs.buscaCep.rest;

import lombok.Getter;

public enum ECodigoRetorno {
	ERRO("Erro"),
	SUCESSO("Sucesso");
	
	@Getter private String descricao;
	
	ECodigoRetorno(String descricao) {
		this.descricao = descricao;
	}
}
