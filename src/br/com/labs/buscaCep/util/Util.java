package br.com.labs.buscaCep.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Random;

public final class Util {

	private Util() {
		
	}
	
	public static String removerCaracteresNaoNumericos(String value) {
		if (value == null) {
			value = "";
		}
		
		value = value.replaceAll("[^\\d]", "").trim();
		value = value.replaceAll("\\+", "");
		return value;
	}

	public static boolean isEmpty(String value) {
		return value == null || value.replaceAll(" ", "").isEmpty();
	}
	
	public static String gerarSenha() {
		try {
			String caracteres = Constantes.NUMEROS 
					+ Constantes.LETRAS_MINUSCULAS
					+ Constantes.LETRAS_MAIUSCULAS
					+ Constantes.NUMEROS;
			Random random = new Random();
			
			String senha = "";
			int ultimaPosicao = caracteres.length() - 1;
			
			for (int i = 0; i < 8; i++) {
				int posicao = random.nextInt(ultimaPosicao);
				senha = senha + caracteres.charAt(posicao);
			}
			
			return senha;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static final String obterJson(Object object) throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw e;
		}
	}
}