package br.com.labs.buscaCep.servico;

import br.com.labs.buscaCep.pojo.Endereco;
import br.com.labs.buscaCep.rest.ECodigoRetorno;
import br.com.labs.buscaCep.rest.cep.BuscaCepEnvio;
import br.com.labs.buscaCep.rest.cep.BuscaCepRetorno;
import br.com.labs.buscaCep.rest.cep.InsereEnderecoEnvio;
import br.com.labs.buscaCep.rest.cep.InsereEnderecoRetorno;
import br.com.labs.buscaCep.servico.exception.ServicoException;
import br.com.labs.buscaCep.util.Constantes;
import br.com.labs.buscaCep.util.Util;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CepServico extends BaseServico {

	private static final long serialVersionUID = 1L;
	
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
				
				if (cep.length() == 8) {
					boolean buscar = true;
					
					while(buscar) {
						Endereco endereco = aplicacao.getEnderecoPorCep(cep);

						if (endereco == null) {
							if (Constantes.CEP_SOMENTE_ZEROS.equals(cep)) {
								buscar = false;
							} else {
								char[] numeros = cep.toCharArray();
								for (int i = 7; i >= 0; i--) {
									if (numeros[i] != Constantes.CHAR_ZERO) {
										numeros[i] = Constantes.CHAR_ZERO;
										break;
									}
	 							}
								cep = String.valueOf(numeros);
	 						}
						} else {
							buscar = false;
						}
					}
				} else {
					retorno.setMensagemRetorno("Cep inválido.");
				}
			}
			
			return retorno;
		} catch (Exception e) {
			throw new ServicoException(e.getMessage());
		}
	}

	public InsereEnderecoRetorno inserir(InsereEnderecoEnvio insereEnderecoEnvio) {
		try {
			InsereEnderecoRetorno retorno = new InsereEnderecoRetorno();
			
			if (insereEnderecoEnvio == null) {
				
			} else if (insereEnderecoEnvio.getEndereco() == null) {
			
			} else if (Util.isEmpty(insereEnderecoEnvio.getEndereco().getCep())) {
				
			} else if (Util.isEmpty(insereEnderecoEnvio.getEndereco().getRua())) {
				
			} else if (Util.isEmpty(insereEnderecoEnvio.getEndereco().getBairro())) {
				
			} else if (Util.isEmpty(insereEnderecoEnvio.getEndereco().getCidade())) {
				
			} else if (Util.isEmpty(insereEnderecoEnvio.getEndereco().getEstado())) {
				
			}
			
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return new InsereEnderecoRetorno(ECodigoRetorno.ERRO.getDescricao(), "Ops! Não foi possível inserir o endereço.");
		}
	}

}
