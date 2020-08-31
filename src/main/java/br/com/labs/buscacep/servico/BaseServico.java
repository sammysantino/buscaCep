package br.com.labs.buscacep.servico;

import br.com.labs.buscacep.dao.InterfaceDAO;
import br.com.labs.buscacep.util.Constantes;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;

public abstract class BaseServico<T> implements InterfaceServico<T> {

	private static final long serialVersionUID = 1L;

	private InterfaceDAO<T> dao;
	
	@Inject
    protected transient Logger log;
	
	protected abstract void inicializar();

	@Override
	public T salvar(T t) throws ServicoException {
		try {
			 T f = dao.salvar(t);
			 return f;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServicoException(Constantes.MENSAGEM_ERRO_PADRAO);
		}
	}

	@Override
	public T alterar(T t) throws ServicoException {
		try {
			return dao.alterar(t);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServicoException(Constantes.MENSAGEM_ERRO_PADRAO);
		}
	}

	@Override
	public void deletar(Integer id) throws ServicoException {
		try {
			dao.deletar(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServicoException(Constantes.MENSAGEM_ERRO_PADRAO);
		}
	}

	@Override
	public List<T> obterTodos() throws ServicoException {
		try {
			return dao.consultarTodos();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new ServicoException(Constantes.MENSAGEM_ERRO_PADRAO);
		}
	}

	protected InterfaceDAO<T> getDao() {
		return dao;
	}

	protected void setDao(InterfaceDAO<T> dao) {
		this.dao = dao;
	}
	
	public Logger getLog() {
		return log;
	}
}
