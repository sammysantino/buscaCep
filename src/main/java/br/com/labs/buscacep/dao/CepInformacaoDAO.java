package br.com.labs.buscacep.dao;

import br.com.labs.buscacep.entidade.CepInformacao;
import br.com.labs.buscacep.util.Constantes;
import javax.persistence.NoResultException;

public class CepInformacaoDAO extends BaseDAO<CepInformacao> {

	private static final long serialVersionUID = 1L;
	
	public CepInformacaoDAO() {
		super(CepInformacao.class);
	}
	
	public CepInformacao consultarPorCep(String cep) throws DAOException {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT e ");
			sql.append("FROM CepInformacao e ");
			sql.append("WHERE e.cep =:_cep ");
			
			return getEm()
					.createQuery(sql.toString(), CepInformacao.class)
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
