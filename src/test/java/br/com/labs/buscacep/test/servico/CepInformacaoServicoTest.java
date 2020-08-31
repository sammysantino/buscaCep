package br.com.labs.buscacep.test.servico;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import br.com.labs.buscacep.dao.CepInformacaoDAO;
import br.com.labs.buscacep.entidade.CepInformacao;
import br.com.labs.buscacep.entidade.mock.CepInformacaoMock;
import br.com.labs.buscacep.rest.ECodigoRetorno;
import br.com.labs.buscacep.rest.cep.BuscaCepEnvio;
import br.com.labs.buscacep.rest.cep.BuscaCepRetorno;
import br.com.labs.buscacep.rest.cep.InsereCepInformacaoEnvio;
import br.com.labs.buscacep.rest.cep.InsereCepInformacaoRetorno;
import br.com.labs.buscacep.servico.AutorizacaoServico;
import br.com.labs.buscacep.servico.CepInformacaoServico;
import br.com.labs.buscacep.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class CepInformacaoServicoTest {
	
	@InjectMocks
	private CepInformacaoServico cepInformacaoServico;
	
	@Mock
	private CepInformacaoDAO cepInformacaoDao;
	
	@Mock
	private AutorizacaoServico autorizacaoServico;
	
	@Before
	public void inicializar() {
		cepInformacaoServico = new CepInformacaoServico(cepInformacaoDao, 
				autorizacaoServico, 
				LoggerFactory.getLogger(CepInformacaoServico.class));
	}
	
	@Test
	public void deveRetornarValidacaoAoBuscarCepInvalido() {
		BuscaCepEnvio envio = new BuscaCepEnvio("admin", "admin");
		envio.setCep("ABCDEFGHIJKLMNOPQRS");
		BuscaCepRetorno retorno = null;
		try {
			retorno = cepInformacaoServico.buscarPorCep(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
		assertNull(retorno.getCep());
	}
	
	@Test
	public void deveRetornarCepInformacaoAoBuscarCepValidoExistente() {
		CepInformacao cepInformacao = CepInformacaoMock.getCepInformacao();
		BuscaCepEnvio envio = new BuscaCepEnvio();
		envio.setCep(cepInformacao.getCep());
		BuscaCepRetorno retorno = null;
		try {
			Mockito.when(cepInformacaoDao.consultarPorCep(envio.getCep())).thenReturn(cepInformacao);
			retorno = cepInformacaoServico.buscarPorCep(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.SUCESSO.getCodigo().equals(retorno.getCodigoRetorno()));
		assertNotNull(retorno.getCep());
	}
	
	
	@Test
	public void naoDeveRetornarCepInformacaoAoBuscarCepValidoInexistente() {
		BuscaCepEnvio envio = new BuscaCepEnvio();
		envio.setCep(Util.gerarCep());
		BuscaCepRetorno retorno = null;
		try {
			retorno = cepInformacaoServico.buscarPorCep(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.SUCESSO.getCodigo().equals(retorno.getCodigoRetorno()));
		assertNull(retorno.getCep());
	}
	
	@Test
	public void deveRetornarValidacaoAoInserirCepInformacaoCepInvalido() {
		InsereCepInformacaoEnvio envio = new InsereCepInformacaoEnvio("admin", "admin");
		CepInformacao cepInformacao = CepInformacaoMock.getCepInformacao();
		cepInformacao.setCep(null);
		envio.setCepInformacao(cepInformacao);
		InsereCepInformacaoRetorno retorno = null;
		try {
			
			retorno = cepInformacaoServico.inserir(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
	}
	
	@Test
	public void deveRetornarValidacaoAoInserirCepInformacaoRuaInvalida() {
		InsereCepInformacaoEnvio envio = new InsereCepInformacaoEnvio("admin", "admin");
		CepInformacao cepInformacao = CepInformacaoMock.getCepInformacao();
		cepInformacao.setRua(null);
		envio.setCepInformacao(cepInformacao);
		InsereCepInformacaoRetorno retorno = null;
		try {
			
			retorno = cepInformacaoServico.inserir(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
	}
	
	@Test
	public void deveRetornarValidacaoAoInserirCepInformacaoBairroInvalido() {
		InsereCepInformacaoEnvio envio = new InsereCepInformacaoEnvio("admin", "admin");
		CepInformacao cepInformacao = CepInformacaoMock.getCepInformacao();
		cepInformacao.setBairro(null);
		envio.setCepInformacao(cepInformacao);
		InsereCepInformacaoRetorno retorno = null;
		try {
			
			retorno = cepInformacaoServico.inserir(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
	}
	
	@Test
	public void deveRetornarValidacaoAoInserirCepInformacaoCidadeInvalida() {
		InsereCepInformacaoEnvio envio = new InsereCepInformacaoEnvio("admin", "admin");
		CepInformacao cepInformacao = CepInformacaoMock.getCepInformacao();
		cepInformacao.setCidade(null);
		envio.setCepInformacao(cepInformacao);
		InsereCepInformacaoRetorno retorno = null;
		try {
			
			retorno = cepInformacaoServico.inserir(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
	}
	
	@Test
	public void deveRetornarValidacaoAoInserirCepInformacaoEstadoInvalido() {
		InsereCepInformacaoEnvio envio = new InsereCepInformacaoEnvio("admin", "admin");
		CepInformacao cepInformacao = CepInformacaoMock.getCepInformacao();
		cepInformacao.setEstado(null);
		envio.setCepInformacao(cepInformacao);
		InsereCepInformacaoRetorno retorno = null;
		try {
			
			retorno = cepInformacaoServico.inserir(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
	}
	
	@Test
	public void deveRetornarSucessoAoInserirCepInformacaoValido() {
		InsereCepInformacaoEnvio envio = new InsereCepInformacaoEnvio("admin", "admin");
		CepInformacao cepInformacao = CepInformacaoMock.getCepInformacao();
		envio.setCepInformacao(cepInformacao);
		InsereCepInformacaoRetorno retorno = null;
		try {
			Mockito.when(cepInformacaoDao.salvar(cepInformacao)).thenReturn(cepInformacao);
			retorno = cepInformacaoServico.inserir(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.SUCESSO.getCodigo().equals(retorno.getCodigoRetorno()));
	}
	
}
