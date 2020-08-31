package br.com.labs.buscacep.dao;

import br.com.labs.buscacep.entidade.Autorizacao;
import br.com.labs.buscacep.util.Constantes;
import javax.persistence.NoResultException;

public class AutorizacaoDAO extends BaseDAO<Autorizacao> {

	private static final long serialVersionUID = 1L;

	public AutorizacaoDAO() {
		super(Autorizacao.class);
	}
	
	public Autorizacao consultarPorLogin(String login) throws DAOException {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT a ");
			sql.append("FROM Autorizacao a ");
			sql.append("WHERE a.login =:_login ");
			
			return getEm()
					.createQuery(sql.toString(), Autorizacao.class)
					.setParameter("_login", login)
					.getSingleResult();
			
		} catch (NoResultException nre) {
			return null;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(Constantes.MENSAGEM_ERRO_PADRAO);
		}
	}

}
