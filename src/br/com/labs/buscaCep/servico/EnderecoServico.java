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
public class EnderecoServico extends BaseServico {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private CredencialServico credencialServico;

	public BuscaCepRetorno buscarPorCep(BuscaCepEnvio buscaCepEnvio) throws ServicoException {
		try {
			BuscaCepRetorno retorno = new BuscaCepRetorno();
			StringBuilder mensagem = new StringBuilder();
			ECodigoRetorno codigoRetorno = ECodigoRetorno.SUCESSO;
			String cep = null;
			
			if (buscaCepEnvio == null) {
				mensagem.append("Dados da requisição inválidos. ");
			} else {
				mensagem.append(credencialServico.autenticar(buscaCepEnvio.getLogin(), buscaCepEnvio.getSenha()));
			
				if (Util.isEmpty(buscaCepEnvio.getCep())) {
					mensagem.append("Cep é obrigatório. ");
				} else  {
					cep = Util.removerCaracteresNaoNumericos(buscaCepEnvio.getCep());
					
					if (cep.length() != 8) {
						mensagem.append("Cep inválido. ");
					}
				}
			}
				
			if (mensagem.toString().isEmpty()) {
				boolean buscar = true;
				
				while(buscar) {
					Endereco endereco = aplicacao.getEnderecoPorCep(cep);

					if (endereco == null) {
						if (Constantes.CEP_SOMENTE_ZEROS.equals(cep)) {
							buscar = false;
							mensagem.append("Endereço não localizado para o CEP ")
									.append(buscaCepEnvio.getCep());
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
						retorno.setEndereco(endereco);
						mensagem.append("Endereço localizado.");
					}
				}
			} else {
				codigoRetorno = ECodigoRetorno.ERRO;
			}
			
			retorno.setCodigoRetorno(codigoRetorno.getDescricao());
			retorno.setMensagemRetorno(mensagem.toString());
			
			return retorno;
		} catch (Exception e) {
			throw new ServicoException(e.getMessage());
		}
	}

	public InsereEnderecoRetorno inserir(InsereEnderecoEnvio insereEnderecoEnvio) {
		try {
			InsereEnderecoRetorno retorno = new InsereEnderecoRetorno();
			StringBuilder validacao = new StringBuilder();
			
			if (insereEnderecoEnvio == null) {
				validacao.append("Dados da requisição inválidos.");
			} 
			if (insereEnderecoEnvio.getEndereco() == null) {
				validacao.append("Endereço é obrigatório. ");
			} 
			
			validacao.append(credencialServico.autenticar(insereEnderecoEnvio.getLogin(), insereEnderecoEnvio.getSenha()));

			if (Util.isEmpty(insereEnderecoEnvio.getEndereco().getCep())) {
				validacao.append("Cep é obrigatório. ");
			} 
			if (Util.isEmpty(insereEnderecoEnvio.getEndereco().getRua())) {
				validacao.append("Rua é obrigatória. ");
			} 
			if (Util.isEmpty(insereEnderecoEnvio.getEndereco().getBairro())) {
				validacao.append("Bairro é obrigatório. ");
			} 
			if (Util.isEmpty(insereEnderecoEnvio.getEndereco().getCidade())) {
				validacao.append("Cidade é obrigatória. ");
			} 
			if (Util.isEmpty(insereEnderecoEnvio.getEndereco().getEstado())) {
				validacao.append("Estado é obrigatório. ");
			} 
			if (validacao.toString().isEmpty()) {
				aplicacao.inserirEnderecoPorCep(insereEnderecoEnvio.getEndereco());
				retorno.setCodigoRetorno(ECodigoRetorno.SUCESSO.getDescricao());
				retorno.setMensagemRetorno("Endereço inserido.");
			} else {
				retorno.setCodigoRetorno(ECodigoRetorno.ERRO.getDescricao());
				retorno.setMensagemRetorno(validacao.toString());
			}
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return new InsereEnderecoRetorno(ECodigoRetorno.ERRO.getDescricao(), "Ops! Não foi possível inserir o endereço.");
		}
	}

}
