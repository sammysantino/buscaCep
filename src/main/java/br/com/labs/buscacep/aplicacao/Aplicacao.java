package br.com.labs.buscacep.aplicacao;

import br.com.labs.buscacep.pojo.Autorizacao;
import br.com.labs.buscacep.pojo.Endereco;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Aplicacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String, Endereco> enderecosPorCep;
	private Map<String, Autorizacao> credenciaisPorLogin;
	
	@PostConstruct
	private void inicializar() {
		enderecosPorCep = new HashMap<>();
		Endereco endereco = new Endereco();
		
		credenciaisPorLogin = new HashMap<>();
		credenciaisPorLogin.put("admin", new Autorizacao("admin", "admin", Calendar.getInstance()));
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
	
	public Autorizacao getCredencialPorLogin(String login) {
		try {
			return credenciaisPorLogin.get(login);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void inserirCredencialPorLogin(Autorizacao autorizacao) {
		try {
			credenciaisPorLogin.put(autorizacao.getLogin(), autorizacao);
		} catch (Exception e) {
			throw e;
		}
	}
	
}