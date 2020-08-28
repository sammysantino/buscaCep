package br.com.labs.buscacep.aplicacao;

import br.com.labs.buscacep.pojo.Autorizacao;
import br.com.labs.buscacep.pojo.Endereco;
import br.com.labs.buscacep.util.EnderecoMock;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import org.slf4j.Logger;

@Named
@ApplicationScoped
public class Aplicacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private transient Logger log;
	
	private Map<String, Endereco> enderecosPorCep;
	private Map<String, Autorizacao> credenciaisPorLogin;
	
	@PostConstruct
	public void inicializar() {
		enderecosPorCep = new HashMap<>();
		for (int i = 0; i < 100; i++) {
			Endereco endereco = EnderecoMock.getEndereco();
			enderecosPorCep.put(endereco.getCep(), endereco);
			log.info("Endereço adicionado, CEP " + endereco.getCep());
		}
		
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