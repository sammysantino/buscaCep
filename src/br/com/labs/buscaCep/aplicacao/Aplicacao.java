package br.com.labs.buscacep.aplicacao;

import br.com.labs.buscacep.pojo.Credencial;
import br.com.labs.buscacep.pojo.Endereco;
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
	private Map<String, Credencial> credenciaisPorLogin;
	
	@PostConstruct
	private void inicializar() {
		enderecosPorCep = new HashMap<>();
		credenciaisPorLogin = new HashMap<>();
	}

	public Endereco getEnderecoPorCep(String cep) {
		try {
			return enderecosPorCep.get(cep);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void inserirEnderecoPorCep(Endereco endereco) {
		try {
			enderecosPorCep.put(endereco.getCep(), endereco);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Credencial getCredencialPorLogin(String login) {
		try {
			return credenciaisPorLogin.get(login);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void inserirCredencialPorLogin(Credencial credencial) {
		try {
			credenciaisPorLogin.put(credencial.getLogin(), credencial);
		} catch (Exception e) {
			throw e;
		}
	}
	
}