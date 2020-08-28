package br.com.labs.buscacep.rest;

import br.com.labs.buscacep.aplicacao.Aplicacao;
import javax.inject.Inject;
import org.slf4j.Logger;

public class BaseRest {

	@Inject
	private transient Logger log;
	
	@Inject
	private Aplicacao configuracaoApplication;
	
	public Logger getLog() {
		return log;
	}
	
	public Aplicacao getConfiguracaoApplication() {
		return configuracaoApplication;
	}
}