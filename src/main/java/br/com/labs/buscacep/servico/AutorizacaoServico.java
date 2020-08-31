package br.com.labs.buscacep.servico;

import br.com.labs.buscacep.dao.AutorizacaoDAO;
import br.com.labs.buscacep.entidade.Autorizacao;
import br.com.labs.buscacep.util.Util;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AutorizacaoServico extends BaseServico<Autorizacao>  {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AutorizacaoDAO dao;
	
	@Override
	@PostConstruct
	protected void inicializar() {
		super.setDao(dao);
	}

	public void autorizar(String login, String senha) throws AutorizacaoException, ServicoException {
		try {
			StringBuilder validacao = new StringBuilder();
			
			if (Util.isNullOrEmpty(login)) {
				validacao.append("Login é obrigatório. ");
			} 

			if (Util.isNullOrEmpty(senha)) {
				validacao.append("Senha é obrigatória. ");
			}
			
			if (validacao.toString().isEmpty()) {
				Autorizacao autorizacao = obterPorLogin(login);
				if (autorizacao == null) {
					validacao.append("Login não localizado. ");
				} else if (!senha.equals(autorizacao.getSenha())) {
					validacao.append("Senha incorreta. ");
				}
			}
			
			if (!validacao.toString().isEmpty()) {
				throw new AutorizacaoException(validacao.toString());
			}
		
		} catch (AutorizacaoException ae) {
			throw ae;
		} catch (Exception e) {
			throw new ServicoException(e.getMessage());
		}
	}

	public Autorizacao obterPorLogin(String login) throws ServicoException {
		try {
			return dao.consultarPorLogin(login);
		} catch (Exception e) {
			throw new ServicoException(e.getMessage());
		}
	}
	
}
