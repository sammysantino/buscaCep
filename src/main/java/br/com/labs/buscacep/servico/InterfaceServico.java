package br.com.labs.buscacep.servico;

import br.com.labs.buscacep.exception.ServicoException;
import java.io.Serializable;
import java.util.List;

public interface InterfaceServico<T> extends Serializable {

	T salvar(T t) throws ServicoException;
	
	T alterar(T t) throws ServicoException;
	
	void deletar(Integer id) throws ServicoException;

	List<T> obterTodos() throws ServicoException;

}
