package br.com.labs.buscaCep.aplicacao;

import br.com.labs.buscaCep.pojo.Endereco;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Aplicacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String, Endereco> enderecosPorCep;
	
	@PostConstruct
	private void inicializar() {
		enderecosPorCep = new HashMap<>();
	}

	public Endereco getEnderecoPorCep(String cep) {
		try {
			return enderecosPorCep.get(cep);
		} catch (Exception e) {
			throw e;
		}
	}
	
}