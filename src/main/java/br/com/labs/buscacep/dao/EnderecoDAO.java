package br.com.labs.buscacep.dao;

import br.com.labs.buscacep.entidade.Endereco;
import br.com.labs.buscacep.util.Constantes;
import javax.persistence.NoResultException;

public class EnderecoDAO extends BaseDAO<Endereco> {

	private static final long serialVersionUID = 1L;
	
	public EnderecoDAO() {
		super(Endereco.class);
	}
	
	public Endereco consultarPorCep(String cep) throws DAOException {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT e ");
			sql.append("FROM Endereco e ");
			sql.append("WHERE e.cep =:_cep ");
			
			return getEm()
					.createQuery(sql.toString(), Endereco.class)
					.setParameter("_cep", cep)
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (Exception e) { 
			e.printStackTrace();
			throw new DAOException(Constantes.MENSAGEM_ERRO_PADRAO);
		}
	}

}
