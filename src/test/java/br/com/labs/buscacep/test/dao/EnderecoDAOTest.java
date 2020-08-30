package br.com.labs.buscacep.test.dao;

import static org.junit.Assert.assertNull;

import br.com.labs.buscacep.dao.EnderecoDAO;
import br.com.labs.buscacep.model.Endereco;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoDAOTest {
	
	private EnderecoDAO dao;
	
    @Before
    public void inicializar() {
    	MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void buscarCepInexistente() {
		String cepInexistente = "ABCDEFGHIJKLMNOPQRS";
		Endereco endereco = null;
		try {
			endereco = dao.consultarPorCep(cepInexistente);
			assertNull(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
