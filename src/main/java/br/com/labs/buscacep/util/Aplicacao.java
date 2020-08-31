package br.com.labs.buscacep.util;

import br.com.labs.buscacep.entidade.Autorizacao;
import br.com.labs.buscacep.servico.AutorizacaoServico;
import br.com.labs.buscacep.servico.CepInformacaoServico;
import java.io.Serializable;
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
	private CepInformacaoServico enderecoServico;
	
	@EJB
	private AutorizacaoServico autorizacaoServico;
	
	@PostConstruct
	public void inicializar() {
		try {
			log.info("INICIALIZAR  REGISTROS PADRAO");
			enderecoServico.inicializarCepInformacaos();
			Autorizacao autorizacaoPadrao = autorizacaoServico.obterPorLogin("admin");
			if (autorizacaoPadrao == null) {
				autorizacaoServico.salvar(new Autorizacao("admin", "admin"));
			}
			log.info("FIM INICIALIZAR REGISTROS PADRAO");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}