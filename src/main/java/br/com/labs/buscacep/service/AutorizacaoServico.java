package br.com.labs.buscacep.service;

import br.com.labs.buscacep.dao.AutorizacaoDAO;
import br.com.labs.buscacep.exception.AutorizacaoException;
import br.com.labs.buscacep.exception.ServicoException;
import br.com.labs.buscacep.model.Autorizacao;
import br.com.labs.buscacep.util.Util;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AutorizacaoServico extends BaseServico  {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AutorizacaoDAO dao;

	public void autenticar(String login, String senha) throws AutorizacaoException, ServicoException {
		try {
			StringBuilder validacao = new StringBuilder();
			
			if (Util.isNullOrEmpty(login)) {
				validacao.append("Login é obrigatório. ");
			} 

			if (Util.isNullOrEmpty(senha)) {
				validacao.append("Senha é obrigatória. ");
			}
			
			if (validacao.toString().isEmpty()) {
				Autorizacao autorizacao = dao.obterPorLogin(login);
				if (autorizacao == null) {
					validacao.append("Login não localizado. ");
				} else if (!senha.equals(autorizacao.getSenha())) {
					validacao.append("Senha incorreta. ");
				}
			}
			
			if (!validacao.toString().isEmpty()) {
				throw new AutorizacaoException(validacao.toString());
			}
		} catch (Exception e) {
			throw new ServicoException(e.getMessage());
		}
	}
	
	public void salvar(Autorizacao autorizacao) throws ServicoException {
		try {
			dao.salvar(autorizacao);
		} catch (Exception e) {
			throw new ServicoException(e.getMessage());
		}
	}
	
	

}
