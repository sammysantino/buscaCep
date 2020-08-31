package br.com.labs.buscacep.dao;

import br.com.labs.buscacep.util.Constantes;
import java.util.List;
import javax.ejb.Local;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import org.slf4j.Logger;

@Local(InterfaceDAO.class)
public abstract class BaseDAO<T> implements InterfaceDAO<T> {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(name = PersistenceUnitConfig.NAME)
	@Getter private EntityManager em;
	private Class<T> classe;
	
	@Inject
	protected transient Logger log;

	public BaseDAO(Class<T> classe) {
		this.classe = classe;
	}

	@Override
	public T salvar(T t) throws DAOException {
		try {
			em.persist(t);
			return t;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException(Constantes.MENSAGEM_ERRO_PADRAO);
		}
	}

	@Override
	public T alterar(T t) throws DAOException {
		try {
			return em.merge(t);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new DAOException(Constantes.MENSAGEM_ERRO_PADRAO);
		}
	}

	@Override
	public void deletar(Integer id) throws DAOException {
		try {
			em.remove(em.getReference(classe, id));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new DAOException(Constantes.MENSAGEM_ERRO_PADRAO);
		}
	}

	@Override
	public List<T> consultarTodos() throws DAOException {
		try {
			StringBuilder sql = new StringBuilder()
					.append("SELECT obj FROM ")
					.append(this.classe.getSimpleName())
					.append(" obj");
			
			return em.createQuery(sql.toString(), classe)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new DAOException(Constantes.MENSAGEM_ERRO_PADRAO);
		}
	}
}
