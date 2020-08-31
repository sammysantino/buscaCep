package br.com.labs.buscacep.dao;

import java.io.Serializable;
import java.util.List;

public interface InterfaceDAO<T> extends Serializable {

	T salvar(T t) throws DAOException;

	T alterar(T t) throws DAOException;

	void deletar(Integer id) throws DAOException;

	List<T> consultarTodos() throws DAOException;

}
