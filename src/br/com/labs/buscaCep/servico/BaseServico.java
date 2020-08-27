package br.com.labs.buscaCep.servico;

import br.com.labs.buscaCep.aplicacao.Aplicacao;
import java.io.Serializable;
import javax.inject.Inject;
import org.slf4j.Logger;

public class BaseServico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	protected Aplicacao aplicacao;
	
	@Inject
	protected transient Logger log;

}
