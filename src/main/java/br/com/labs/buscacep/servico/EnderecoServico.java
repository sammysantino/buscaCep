package br.com.labs.buscacep.servico;

import br.com.labs.buscacep.pojo.Endereco;
import br.com.labs.buscacep.rest.ECodigoRetorno;
import br.com.labs.buscacep.rest.cep.BuscaCepEnvio;
import br.com.labs.buscacep.rest.cep.BuscaCepRetorno;
import br.com.labs.buscacep.rest.cep.InsereEnderecoEnvio;
import br.com.labs.buscacep.rest.cep.InsereEnderecoRetorno;
import br.com.labs.buscacep.servico.exception.ServicoException;
import br.com.labs.buscacep.util.Constantes;
import br.com.labs.buscacep.util.Util;
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
			
				if (Util.isNullOrEmpty(buscaCepEnvio.getCep())) {
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
					log.info("Buscando CEP " + cep);
					Endereco endereco = aplicacao.getEnderecoPorCep(cep);

					if (endereco == null) {
						if (Constantes.CEP_SOMENTE_ZEROS.equals(cep)) {
							buscar = false;
							mensagem.append("Endereço não localizado. CEP ")
									.append(buscaCepEnvio.getCep());
						} else {
							cep = Util.substituirUltimoPorZero(cep);
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
			e.printStackTrace();
			return new BuscaCepRetorno(ECodigoRetorno.ERRO.getDescricao(), "Ops! Não foi possível buscar o cep.");
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

			if (Util.isNullOrEmpty(insereEnderecoEnvio.getEndereco().getCep())) {
				validacao.append("Cep é obrigatório. ");
			} 
			if (Util.isNullOrEmpty(insereEnderecoEnvio.getEndereco().getRua())) {
				validacao.append("Rua é obrigatória. ");
			} 
			if (Util.isNullOrEmpty(insereEnderecoEnvio.getEndereco().getBairro())) {
				validacao.append("Bairro é obrigatório. ");
			} 
			if (Util.isNullOrEmpty(insereEnderecoEnvio.getEndereco().getCidade())) {
				validacao.append("Cidade é obrigatória. ");
			} 
			if (Util.isNullOrEmpty(insereEnderecoEnvio.getEndereco().getEstado())) {
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