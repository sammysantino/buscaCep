package br.com.labs.buscacep.mock.model;

import br.com.labs.buscacep.model.Endereco;
import br.com.labs.buscacep.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * classe auxiliar para gerar mocks de endereco
 * @author samara
 *
 */
public final class EnderecoMock {
	
	private static Logger log;
	private static Map<String, List<String>> cidadesPorUf;
	private static List<String> tiposlogradouros;
	private static List<String> nomesLogradouros;
	private static List<String> nomesBairros;
	
	static {
		LoggerFactory.getLogger(EnderecoMock.class);
		
		cidadesPorUf = new HashMap<>();
		
		List<String> cidades = Arrays.asList("Londrina", "Maringá", "Apucarana", "Ibiporã", "Curitiba");
		cidadesPorUf.put("Paraná", cidades);
		
		cidades = Arrays.asList("São Paulo", "Franca", "Jundiaí", "Campinas", "Lins");
		cidadesPorUf.put("São Paulo", cidades);
		
		cidades = Arrays.asList("Campo Grande", "Dourados", "Bonito");
		cidadesPorUf.put("Mato Grosso do Sul", cidades);
		
		cidades = Arrays.asList("Rio de Janeiro ", "Paraty", "Rio das Ostras", "Búzios");
		cidadesPorUf.put("", cidades);
		
		tiposlogradouros = Arrays.asList("Rua", "Avenida", "Alameda", "Travessa");
		nomesLogradouros = Arrays.asList("", "Avenida", "Alameda", "Travessa");
		nomesBairros = Arrays.asList("", "Avenida", "Alameda", "Travessa");
	}
	
	public static Endereco getEndereco() {
		List<String> estados = new ArrayList<>(cidadesPorUf.keySet());
		String estado = estados.get(getRandomIndex(estados));
		List<String> cidades = cidadesPorUf.get(estado);
		String cidade = cidades.get(getRandomIndex(cidades));
		String tipoLogradouro = tiposlogradouros.get(getRandomIndex(tiposlogradouros));
		String nomeLogradouro = nomesLogradouros.get(getRandomIndex(nomesLogradouros));
		String bairro = nomesBairros.get(getRandomIndex(nomesBairros));
		
		Endereco endereco = new Endereco();
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setRua(tipoLogradouro + " " + nomeLogradouro);
		endereco.setBairro(bairro);
		endereco.setCep(Util.gerarCep());
		
		return endereco;
	}
	
	private static final int getRandomIndex(Collection collection) {
		int retorno = 0;
		try {
			if (!Util.isNullOrEmpty(collection)) {
				retorno = new Random().nextInt(collection.size() - 1);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return retorno;
	}
}
