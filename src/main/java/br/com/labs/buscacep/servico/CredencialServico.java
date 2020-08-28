package br.com.labs.buscacep.servico;

import br.com.labs.buscacep.pojo.Autorizacao;
import br.com.labs.buscacep.servico.exception.ServicoException;
import br.com.labs.buscacep.util.Util;
import javax.ejb.Stateless;

@Stateless
public class CredencialServico  extends BaseServico  {

	private static final long serialVersionUID = 1L;

	public String autenticar(String login, String senha) throws ServicoException {
		try {
			StringBuilder validacao = new StringBuilder();
			
			if (Util.isNullOrEmpty(login)) {
				validacao.append("Login é obrigatório. ");
			} 

			if (Util.isNullOrEmpty(senha)) {
				validacao.append("Senha é obrigatória. ");
			}
			
			if (validacao.toString().isEmpty()) {
				Autorizacao credencial = aplicacao.getCredencialPorLogin(login);
				if (credencial == null) {
					validacao.append("Login não localizado. ");
				} else if (!senha.equals(credencial.getSenha())) {
					validacao.append("Senha incorreta. ");
				}
			}
			
			return validacao.toString();
		} catch (Exception e) {
			throw new ServicoException(e.getMessage());
		}
		
	}

}
