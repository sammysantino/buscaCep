package br.com.labs.buscacep.test.servico;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import br.com.labs.buscacep.dao.EnderecoDAO;
import br.com.labs.buscacep.mock.model.EnderecoMock;
import br.com.labs.buscacep.model.Endereco;
import br.com.labs.buscacep.rest.ECodigoRetorno;
import br.com.labs.buscacep.rest.cep.BuscaCepEnvio;
import br.com.labs.buscacep.rest.cep.BuscaCepRetorno;
import br.com.labs.buscacep.rest.cep.InsereEnderecoEnvio;
import br.com.labs.buscacep.rest.cep.InsereEnderecoRetorno;
import br.com.labs.buscacep.service.AutorizacaoServico;
import br.com.labs.buscacep.service.EnderecoServico;
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
public class EnderecoServicoTest {
	
	@InjectMocks
	private EnderecoServico enderecoServico;
	
	@Mock
	private EnderecoDAO enderecoDao;
	
	@Mock
	private AutorizacaoServico autorizacaoServico;
	
	@Before
	public void inicializar() {
		enderecoServico = new EnderecoServico(enderecoDao, autorizacaoServico, LoggerFactory.getLogger(EnderecoServico.class));
	}
	
	@Test
	public void buscarCepInvalido() {
		BuscaCepEnvio envio = new BuscaCepEnvio();
		envio.setCep("ABCDEFGHIJKLMNOPQRS");
		BuscaCepRetorno retorno = null;
		try {
			retorno = enderecoServico.obterPorCep(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
		assertNull(retorno.getEndereco());
	}
	
	@Test
	public void buscarCepValidoExistente() {
		Endereco endereco = EnderecoMock.getEndereco();
		BuscaCepEnvio envio = new BuscaCepEnvio();
		envio.setCep(endereco.getCep());
		BuscaCepRetorno retorno = null;
		try {
			Mockito.when(enderecoDao.consultarPorCep(envio.getCep())).thenReturn(endereco);
			retorno = enderecoServico.obterPorCep(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.SUCESSO.getCodigo().equals(retorno.getCodigoRetorno()));
		assertNotNull(retorno.getEndereco());
	}
	
	
	@Test
	public void buscarCepValidoInexistente() {
		BuscaCepEnvio envio = new BuscaCepEnvio();
		envio.setCep(Util.gerarCep());
		BuscaCepRetorno retorno = null;
		try {
			retorno = enderecoServico.obterPorCep(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.SUCESSO.getCodigo().equals(retorno.getCodigoRetorno()));
		assertNull(retorno.getEndereco());
	}
	
	@Test
	public void inserirEnderecoCepInvalido() {
		InsereEnderecoEnvio envio = new InsereEnderecoEnvio();
		Endereco endereco = EnderecoMock.getEndereco();
		endereco.setCep(null);
		envio.setEndereco(endereco);
		InsereEnderecoRetorno retorno = null;
		try {
			retorno = enderecoServico.inserir(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
	}
	
	@Test
	public void inserirEnderecoRuaInvalida() {
		InsereEnderecoEnvio envio = new InsereEnderecoEnvio();
		Endereco endereco = EnderecoMock.getEndereco();
		endereco.setRua(null);
		envio.setEndereco(endereco);
		InsereEnderecoRetorno retorno = null;
		try {
			retorno = enderecoServico.inserir(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
	}
	
	@Test
	public void inserirEnderecoBairroInvalido() {
		InsereEnderecoEnvio envio = new InsereEnderecoEnvio();
		Endereco endereco = EnderecoMock.getEndereco();
		endereco.setBairro(null);
		envio.setEndereco(endereco);
		InsereEnderecoRetorno retorno = null;
		try {
			retorno = enderecoServico.inserir(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
	}
	
	@Test
	public void inserirEnderecoCidadeInvalida() {
		InsereEnderecoEnvio envio = new InsereEnderecoEnvio();
		Endereco endereco = EnderecoMock.getEndereco();
		endereco.setCidade(null);
		envio.setEndereco(endereco);
		InsereEnderecoRetorno retorno = null;
		try {
			retorno = enderecoServico.inserir(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
	}
	
	@Test
	public void inserirEnderecoEstadoInvalido() {
		InsereEnderecoEnvio envio = new InsereEnderecoEnvio();
		Endereco endereco = EnderecoMock.getEndereco();
		endereco.setEstado(null);
		envio.setEndereco(endereco);
		InsereEnderecoRetorno retorno = null;
		try {
			retorno = enderecoServico.inserir(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(retorno);
		assertTrue(ECodigoRetorno.VALIDACAO.getCodigo().equals(retorno.getCodigoRetorno()));
	}
}
