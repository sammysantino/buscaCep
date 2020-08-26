package br.com.labs.buscaCep.servico;

import br.com.labs.buscaCep.rest.BuscaCepEnvio;
import br.com.labs.buscaCep.rest.BuscaCepRetorno;
import br.com.labs.buscaCep.servico.exception.ServicoException;
import br.com.labs.buscaCep.util.Util;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.tukaani.xz.SingleXZInputStream;

@Stateless
public class CepServico {
	
	@EJB
	private CredencialServico credencialServico;

	public BuscaCepRetorno buscar(BuscaCepEnvio buscaCepEnvio) throws ServicoException {
		try {
			BuscaCepRetorno retorno = new BuscaCepRetorno();
			
			if (buscaCepEnvio == null) {
				retorno.setMensagemRetorno("Dados da requisição inválidos.");
			} else if (Util.isEmpty(buscaCepEnvio.getLogin())) {
				retorno.setMensagemRetorno("Login inválido.");
			} else if (Util.isEmpty(buscaCepEnvio.getSenha())) {
				retorno.setMensagemRetorno("Senha inválida.");
			} else if (!credencialServico.autenticar(buscaCepEnvio.getLogin(), buscaCepEnvio.getSenha())) {
				retorno.setMensagemRetorno("Credencial inválida.");
			} else if (Util.isEmpty(buscaCepEnvio.getCep())) {
				retorno.setMensagemRetorno("Cep inválido.");
			} else {
				String cep = Util.removerCaracteresNaoNumericos(buscaCepEnvio.getCep());
				
				if (cep.length() != 8) {
					
				} else {
					boolean buscar = true;
					
					while(buscar) {
						//buscar
						// se encontrou buscar = false
						
						
						//se nao encontrou
						if (cep.equals("00000000")) {
							buscar = false;
						} else {
							char[] numeros = cep.toCharArray();
							for (int i = 7; i == 0; i--) {
								if (numeros[i] != '0') {
									numeros[i] = '0';
									break;
								}
 							}
							cep = String.valueOf(numeros);
 						}
					}
				}
			}
			
			return retorno;
		} catch (Exception e) {
			throw new ServicoException(e.getMessage());
		}
	}

}
