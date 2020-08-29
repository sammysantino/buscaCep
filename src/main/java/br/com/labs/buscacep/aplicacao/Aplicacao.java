package br.com.labs.buscacep.aplicacao;

import br.com.labs.buscacep.model.Autorizacao;
import br.com.labs.buscacep.service.AutorizacaoServico;
import br.com.labs.buscacep.service.EnderecoServico;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.slf4j.Logger;

@Startup
@Singleton
@ApplicationScoped
public class Aplicacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private transient Logger log;
	
	@EJB
	private EnderecoServico enderecoServico;
	
	@EJB
	private AutorizacaoServico autorizacaoServico;
	
	@PostConstruct
	public void inicializar() {
		try {
			enderecoServico.inicializarEnderecos();
			autorizacaoServico.salvar(new Autorizacao("admin", "admin", LocalDateTime.now()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}