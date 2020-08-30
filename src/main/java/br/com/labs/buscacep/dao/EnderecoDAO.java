package br.com.labs.buscacep.dao;

import br.com.labs.buscacep.model.Endereco;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class EnderecoDAO {
	
	private Map<String, Endereco> enderecosPorCep;
	
	@PostConstruct
	private void inicializar() {
		enderecosPorCep = new HashMap<>();
	}
	
	public Endereco consultarPorCep(String cep) {
		try {
			return enderecosPorCep.get(cep);
		} catch (Exception e) { 
			throw e;
		}
	}
	
	public void salvar(Endereco endereco) {
		try {
			enderecosPorCep.put(endereco.getCep(), endereco);
		} catch (Exception e) {
			throw e;
		}
	}

}
