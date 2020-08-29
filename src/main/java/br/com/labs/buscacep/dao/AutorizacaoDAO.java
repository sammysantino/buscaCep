package br.com.labs.buscacep.dao;

import br.com.labs.buscacep.model.Autorizacao;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AutorizacaoDAO {
	
	private Map<String, Autorizacao> credenciaisPorLogin;
	
	@PostConstruct
	private void inicializar() {
		credenciaisPorLogin = new HashMap<>();
	}
	
	public Autorizacao obterPorLogin(String login) {
		try {
			return credenciaisPorLogin.get(login);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void salvar(Autorizacao autorizacao) {
		try {
			credenciaisPorLogin.put(autorizacao.getLogin(), autorizacao);
		} catch (Exception e) {
			throw e;
		}
	}

}
