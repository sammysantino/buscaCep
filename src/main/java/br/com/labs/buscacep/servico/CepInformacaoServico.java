package br.com.labs.buscacep.servico;

import br.com.labs.buscacep.dao.CepInformacaoDAO;
import br.com.labs.buscacep.entidade.CepInformacao;
import br.com.labs.buscacep.entidade.mock.CepInformacaoMock;
import br.com.labs.buscacep.rest.ECodigoRetorno;
import br.com.labs.buscacep.rest.cep.BuscaCepEnvio;
import br.com.labs.buscacep.rest.cep.BuscaCepInformacaoEnvio;
import br.com.labs.buscacep.rest.cep.BuscaCepInformacaoRetorno;
import br.com.labs.buscacep.rest.cep.BuscaCepRetorno;
import br.com.labs.buscacep.rest.cep.InsereCepInformacaoEnvio;
import br.com.labs.buscacep.rest.cep.InsereCepInformacaoRetorno;
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
public class CepInformacaoServico extends BaseServico<CepInformacao> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CepInformacaoDAO dao;
	
	@EJB
	private AutorizacaoServico autorizacaoServico;
	
	public CepInformacaoServico(CepInformacaoDAO dao, AutorizacaoServico autorizacaoServico, Logger log) {
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
					CepInformacao endereco = obterPorCep(cep);

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
						retorno.setCep(endereco);
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

	private CepInformacao obterPorCep(String cep) throws ServicoException {
		try {
			return dao.consultarPorCep(cep);
		} catch (Exception e) {
			throw new ServicoException(e.getMessage());
		}
	}

	public InsereCepInformacaoRetorno inserir(InsereCepInformacaoEnvio insereCepInformacaoEnvio) {
		try {
			StringBuilder validacao = new StringBuilder();
			
			if (insereCepInformacaoEnvio == null) {
				validacao.append("Dados da requisição inválidos.");
			}  else {
				try {
					autorizacaoServico.autorizar(insereCepInformacaoEnvio.getLogin(), insereCepInformacaoEnvio.getSenha());
				} catch (AutorizacaoException ae) {
					validacao.append(ae.getMessage());
				}
				
				if (insereCepInformacaoEnvio.getCepInformacao() == null) {
					validacao.append("Informações do cep são obrigatórias. ");
				} 
				if (Util.isNullOrEmpty(insereCepInformacaoEnvio.getCepInformacao().getCep())) {
					validacao.append("Cep é obrigatório. ");
				} else if (obterPorCep(insereCepInformacaoEnvio.getCepInformacao().getCep()) != null) {
					validacao.append("Já existe um endereço cadastrado para o CEP informado. ");
				}
				if (Util.isNullOrEmpty(insereCepInformacaoEnvio.getCepInformacao().getRua())) {
					validacao.append("Rua é obrigatória. ");
				} 
				if (Util.isNullOrEmpty(insereCepInformacaoEnvio.getCepInformacao().getBairro())) {
					validacao.append("Bairro é obrigatório. ");
				} 
				if (Util.isNullOrEmpty(insereCepInformacaoEnvio.getCepInformacao().getCidade())) {
					validacao.append("Cidade é obrigatória. ");
				} 
				if (Util.isNullOrEmpty(insereCepInformacaoEnvio.getCepInformacao().getEstado())) {
					validacao.append("Estado é obrigatório. ");
				} 
			}
			
			InsereCepInformacaoRetorno retorno = new InsereCepInformacaoRetorno();
			if (validacao.toString().isEmpty()) {
				dao.salvar(insereCepInformacaoEnvio.getCepInformacao());
				retorno.setCodigoRetorno(ECodigoRetorno.SUCESSO.getCodigo());
				retorno.setMensagemRetorno("Endereço inserido.");
			} else {
				retorno.setCodigoRetorno(ECodigoRetorno.VALIDACAO.getCodigo());
				retorno.setMensagemRetorno(validacao.toString());
			}
			return retorno;
		} catch (Exception e) {
			log.error(e.getMessage());
			return new InsereCepInformacaoRetorno(ECodigoRetorno.ERRO.getCodigo(), "Ops! Não foi possível inserir o endereço.");
		}
	}

	/**
	 * inicializa o repositorio de enderecos com registros
	 * @throws ServicoException
	 */
	public void inicializarCepInformacaos() throws ServicoException {
		try {
			List<CepInformacao> enderecos = obterTodos();
			
			if (Util.isNullOrEmpty(enderecos)) {
				//registros aleatorios para volume
				for (int i = 0; i < 10; i++) {
					CepInformacao endereco = CepInformacaoMock.getCepInformacao();
					dao.salvar(endereco);
				}
				
				//registros controlados para testes
				dao.salvar(new CepInformacao.CepInformacaoBuilder()
						.cep("14403471")
						.rua("R. Arnulfo de Lima")
						.bairro("Vila Santa Cruz")
						.cidade("Franca")
						.estado("São Paulo")
						.build());
				
				dao.salvar(new CepInformacao.CepInformacaoBuilder()
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

	public BuscaCepInformacaoRetorno buscarTodos(BuscaCepInformacaoEnvio buscaCepInformacaoEnvio) {
		BuscaCepInformacaoRetorno retorno = new BuscaCepInformacaoRetorno();
		try {
			autorizacaoServico.autorizar(buscaCepInformacaoEnvio.getLogin(), buscaCepInformacaoEnvio.getSenha());
			List<CepInformacao> enderecos = obterTodos();
			retorno = new BuscaCepInformacaoRetorno(ECodigoRetorno.SUCESSO.getCodigo(), "Busca realizada.", enderecos) ;
		} catch (AutorizacaoException ae) {
			retorno = new BuscaCepInformacaoRetorno(ECodigoRetorno.VALIDACAO.getCodigo(), ae.getMessage()) ;
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new BuscaCepInformacaoRetorno(ECodigoRetorno.ERRO.getCodigo(), "Erro ao buscar endereços.") ;
		}
		return retorno;
	}
}
