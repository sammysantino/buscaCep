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
import br.com.labs.buscacep.service.AutorizacaoServico;
import br.com.labs.buscacep.service.EnderecoServico;
import br.com.labs.buscacep.util.Constantes;
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
		assertTrue(ECodigoRetorno.ERRO.getDescricao().equals(retorno.getCodigoRetorno()));
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
		assertTrue(ECodigoRetorno.SUCESSO.getDescricao().equals(retorno.getCodigoRetorno()));
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
		assertTrue(ECodigoRetorno.SUCESSO.getDescricao().equals(retorno.getCodigoRetorno()));
		assertNull(retorno.getEndereco());
	}
	
}
