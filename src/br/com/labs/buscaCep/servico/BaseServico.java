package br.com.labs.buscaCep.servico;

import br.com.labs.buscaCep.aplicacao.Aplicacao;
import java.io.Serializable;
import javax.inject.Inject;

public class BaseServico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	protected Aplicacao aplicacao;

}
