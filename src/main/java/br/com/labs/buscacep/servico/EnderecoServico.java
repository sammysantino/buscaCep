package br.com.labs.buscacep.servico;

import br.com.labs.buscacep.dao.EnderecoDAO;
import br.com.labs.buscacep.entidade.Endereco;
import br.com.labs.buscacep.entidade.mock.EnderecoMock;
import br.com.labs.buscacep.exception.AutorizacaoException;
import br.com.labs.buscacep.exception.ServicoException;
import br.com.labs.buscacep.rest.ECodigoRetorno;
import br.com.labs.buscacep.rest.endereco.BuscaCepEnvio;
import br.com.labs.buscacep.rest.endereco.BuscaCepRetorno;
import br.com.labs.buscacep.rest.endereco.BuscaEnderecoEnvio;
import br.com.labs.buscacep.rest.endereco.BuscaEnderecoRetorno;
import br.com.labs.buscacep.rest.endereco.InsereEnderecoEnvio;
import br.com.labs.buscacep.rest.endereco.InsereEnderecoRetorno;
import br.com.labs.buscacep.util.Constantes;
import br.com.labs.buscacep.util.Util;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;

@NoArgsConstructor
@Stateless
public class EnderecoServico extends BaseServico<Endereco> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnderecoDAO dao;
	
	@EJB
	private AutorizacaoServico autorizacaoServico;
	
	public EnderecoServico(EnderecoDAO dao, AutorizacaoServico autorizacaoServico, Logger log) {
		this.dao = dao;
		this.autorizacaoServico = autorizacaoServico;
		this.log = log;
	}
	
	@Override
	@PostConstruct
	protected void inicializar() {
		super.setDao(dao);
	}

	public BuscaCepRetorno buscarPorCep(BuscaCepEnvio buscaCepEnvio) throws ServicoException {
		try {
			BuscaCepRetorno retorno = new BuscaCepRetorno();
			StringBuilder mensagem = new StringBuilder();
			ECodigoRetorno codigoRetorno = ECodigoRetorno.SUCESSO;
			String cep = null;
			
			if (buscaCepEnvio == null) {
				mensagem.append("Dados da requisição inválidos. ");
			} else {
				try {
					autorizacaoServico.autorizar(buscaCepEnvio.getLogin(), buscaCepEnvio.getSenha());
				} catch (AutorizacaoException ae) {
					mensagem.append(ae.getMessage());
				}
			
				if (Util.isNullOrEmpty(buscaCepEnvio.getCep())) {
					mensagem.append("Cep é obrigatório. ");
				} else  {
					cep = buscaCepEnvio
							.getCep()
							.replace("-", "")
							.replaceAll(" ", "");
					
					if (cep.length() != 8 || Util.removerCaracteresNaoNumericos(cep).length() != 8) {
						mensagem.append("Cep inválido. ");
					}
				}
			}
				
			if (mensagem.toString().isEmpty() && !Util.isNullOrEmpty(cep)) {
				boolean buscar = true;
				
				//enquanto nao encontrar o cep e cep != '00000000'
				while(buscar) {
					log.info("Buscando CEP " + cep);
					Endereco endereco = obterPorCep(cep);

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
				codigoRetorno = ECodigoRetorno.VALIDACAO;
			}
			retorno.setCodigoRetorno(codigoRetorno.getCodigo());
			retorno.setMensagemRetorno(mensagem.toString());
			return retorno;
		} catch (Exception e) {
			log.error(e.getMessage());
			return new BuscaCepRetorno(ECodigoRetorno.ERRO.getCodigo(), "Ops! Não foi possível buscar o cep.");
		}
	}

	private Endereco obterPorCep(String cep) throws ServicoException {
		try {
			return dao.consultarPorCep(cep);
		} catch (Exception e) {
			throw new ServicoException(e.getMessage());
		}
	}

	public InsereEnderecoRetorno inserir(InsereEnderecoEnvio insereEnderecoEnvio) {
		try {
			StringBuilder validacao = new StringBuilder();
			
			if (insereEnderecoEnvio == null) {
				validacao.append("Dados da requisição inválidos.");
			} 

			try {
				autorizacaoServico.autorizar(insereEnderecoEnvio.getLogin(), insereEnderecoEnvio.getSenha());
			} catch (AutorizacaoException ae) {
				validacao.append(ae.getMessage());
			}
			
			if (insereEnderecoEnvio.getEndereco() == null) {
				validacao.append("Endereço é obrigatório. ");
			} 
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
			
			InsereEnderecoRetorno retorno = new InsereEnderecoRetorno();
			if (validacao.toString().isEmpty()) {
				dao.salvar(insereEnderecoEnvio.getEndereco());
				retorno.setCodigoRetorno(ECodigoRetorno.SUCESSO.getCodigo());
				retorno.setMensagemRetorno("Endereço inserido.");
			} else {
				retorno.setCodigoRetorno(ECodigoRetorno.VALIDACAO.getCodigo());
				retorno.setMensagemRetorno(validacao.toString());
			}
			return retorno;
		} catch (Exception e) {
			log.error(e.getMessage());
			return new InsereEnderecoRetorno(ECodigoRetorno.ERRO.getCodigo(), "Ops! Não foi possível inserir o endereço.");
		}
	}

	/**
	 * inicializa o repositorio de enderecos com registros
	 * @throws ServicoException
	 */
	public void inicializarEnderecos() throws ServicoException {
		try {
			List<Endereco> enderecos = obterTodos();
			
			if (Util.isNullOrEmpty(enderecos)) {
				//registros aleatorios para volume
				for (int i = 0; i < 10; i++) {
					Endereco endereco = EnderecoMock.getEndereco();
					dao.salvar(endereco);
				}
				
				//registros controlados para testes
				dao.salvar(new Endereco.EnderecoBuilder()
						.cep("14403471")
						.rua("R. Arnulfo de Lima")
						.bairro("Vila Santa Cruz")
						.cidade("Franca")
						.estado("São Paulo")
						.build());
				
				dao.salvar(new Endereco.EnderecoBuilder()
						.cep("02047000")
						.rua("R. Maria Prestes Maia")
						.bairro("Vila Guilherme")
						.cidade("São Paulo")
						.estado("São Paulo")
						.build());
			}
		} catch (Exception e) {
			throw new ServicoException(e.getMessage());
		}
	}

	public BuscaEnderecoRetorno obterTodos(BuscaEnderecoEnvio buscaEnderecoEnvio) {
		BuscaEnderecoRetorno retorno = new BuscaEnderecoRetorno();
		try {
			List<Endereco> enderecos = dao.consultarTodos();
			retorno = new BuscaEnderecoRetorno(ECodigoRetorno.SUCESSO.getCodigo(), "Busca realizada.", enderecos) ;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao buscar endereços");
			retorno = new BuscaEnderecoRetorno(ECodigoRetorno.ERRO.getCodigo(), "Erro ao buscar endereços.") ;
		}
		return retorno;
	}
}
