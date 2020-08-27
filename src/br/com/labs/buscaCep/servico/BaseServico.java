package br.com.labs.buscacep.servico;

import br.com.labs.buscacep.aplicacao.Aplicacao;
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
