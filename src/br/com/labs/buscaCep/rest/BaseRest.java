package br.com.labs.buscaCep.rest;

import br.com.labs.buscaCep.componente.ConfiguracaoApplication;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class BaseRest {

	@Inject
	private transient Logger log;
	
	@Inject
	private ConfiguracaoApplication configuracaoApplication;
	
	protected void logInfoRequest(HttpServletRequest request) {
		StringBuilder sbLog = montarMensagem(request);
		log.info(sbLog.toString());
	}
	
	protected void logErrorRequest(HttpServletRequest request, String mensagem) {
		StringBuilder sbLog = montarMensagem(request);
		log.error(mensagem + " " + sbLog.toString());
	}

	private StringBuilder montarMensagem(HttpServletRequest request) {
		StringBuilder sbLog = new StringBuilder();
		sbLog.append("REST LOG: ");
		sbLog.append(request.getMethod());
		sbLog.append("/").append(request.getRemoteAddr());
		sbLog.append(request.getPathInfo());
		sbLog.append(" | ").append("buscaCepApp");
		return sbLog;
	}
	
	public Logger getLog() {
		return log;
	}
	
	public ConfiguracaoApplication getConfiguracaoApplication() {
		return configuracaoApplication;
	}
}