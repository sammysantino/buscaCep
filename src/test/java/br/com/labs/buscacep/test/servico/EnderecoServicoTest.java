package br.com.labs.buscacep.test.servico;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import br.com.labs.buscacep.rest.ECodigoRetorno;
import br.com.labs.buscacep.rest.cep.BuscaCepEnvio;
import br.com.labs.buscacep.rest.cep.BuscaCepRetorno;
import br.com.labs.buscacep.service.EnderecoServico;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoServicoTest {
	
	private EnderecoServico enderecoServico = new EnderecoServico();
	
	@Test
	public void buscarCepInvalido() {
		BuscaCepEnvio envio = new BuscaCepEnvio();
		envio.setCep("123A456B789C");
		BuscaCepRetorno retorno = null;
		try {
			retorno = enderecoServico.obterPorCep(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(ECodigoRetorno.ERRO.getDescricao().equals(retorno.getCodigoRetorno()));
		assertNull(retorno.getEndereco());
	}
	
	@Test
	public void buscarCepValidoExistente() {
		BuscaCepEnvio envio = new BuscaCepEnvio();
		envio.setCep("14403-471");
		
		
		BuscaCepRetorno retorno = null;
		try {
			retorno = enderecoServico.obterPorCep(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(ECodigoRetorno.SUCESSO.getDescricao().equals(retorno.getCodigoRetorno()));
		assertNotNull(retorno.getEndereco());
	}
	
	@Test
	public void buscarCepValidoInexistente() {
		BuscaCepEnvio envio = new BuscaCepEnvio();
		envio.setCep("02047-111");
		BuscaCepRetorno retorno = null;
		try {
			retorno = enderecoServico.obterPorCep(envio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(ECodigoRetorno.SUCESSO.getDescricao().equals(retorno.getCodigoRetorno()));
		assertNull(retorno.getEndereco());
	}
	
	//R. Arnulfo de Lima, 2385 - Vila Santa Cruz, Franca - SP, 14403-471
		//Rod. Mun. Domingos Innocentini, 9303 - Vila Morumbi, São Carlos - SP
		//R. Maria Prestes Maia, 300 - Vila Guilherme, São Paulo - SP, 02047-000
	
	
}
