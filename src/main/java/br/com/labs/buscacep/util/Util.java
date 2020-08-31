package br.com.labs.buscacep.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.Random;

public final class Util {

	private Util() {
		
	}
	
	/**
	 * 
	 * @param String value
	 * @return
	 */
	public static String removerCaracteresNaoNumericos(String value) {
		if (value == null) {
			value = "";
		}
		
		value = value.replaceAll("[^\\d]", "").trim();
		value = value.replaceAll("\\+", "");
		return value;
	}

	public static boolean isNullOrEmpty(String value) {
		return value == null || value.replaceAll(" ", "").isEmpty();
	}
	
	public static boolean isNullOrEmpty(Collection collection) {
		return collection == null || collection.isEmpty();
	}
	
	
	/**
	 * Gera 15 caracteres embaralhados dentre letras e numeros
	 * @return String senha
	 */
	public static String gerarSenha() {
		try {
			String caracteres = Constantes.NUMEROS 
								+ Constantes.LETRAS.toLowerCase()
								+ Constantes.NUMEROS
								+ Constantes.NUMEROS
								+ Constantes.LETRAS.toUpperCase()
								+ Constantes.NUMEROS;
			
			StringBuilder senha = new StringBuilder();
			for (int i = 0; i < 15; i++) {
				senha.append(getRandomItem(caracteres));
			}
			caracteres = null;
			return senha.toString();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * gera uma combinação aleatoria de 8 numeros, no formato String
	 * @return String cep
	 */
	public static final String gerarCep() {
		StringBuilder cep = new StringBuilder();
		try {
			String caracteres = Constantes.NUMEROS 
					+ Constantes.NUMEROS
					+ Constantes.NUMEROS
					+ Constantes.NUMEROS;
			
			for (int i = 0; i < 8; i++) {
				cep.append(Util.getRandomItem(caracteres));
			}
			caracteres = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cep.toString();
	}
	
	/**
	 * retorna um item (caractere) de uma string randomicamente
	 * @param caracteres
	 * @return
	 */
	public static String getRandomItem(String caracteres) {
		String retorno = "";
		if (!isNullOrEmpty(caracteres)) {
			int ultimaPosicao = caracteres.length() - 1;
			Random random = new Random();
			int posicao = random.nextInt(ultimaPosicao);
			retorno = String.valueOf(caracteres.charAt(posicao));
		}
		return retorno;
	}
	
	/**
	 * Retorna o objeto em String Json
	 * @param Object object
	 * @return String objToJson
	 * @throws Exception
	 */
	public static final String getJson(Object object) throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * substitui o ultimo caractere diferente de 0 por 0
	 * @param String texto
	 * @return 
	 */
	public static String substituirUltimoPorZero(String texto) {
		try {
			String retorno = "";
			if (!isNullOrEmpty(texto)) {
				int lastIndex = texto.length() - 1;
				char[] caracteres = texto.toCharArray();
				
				for (int i = lastIndex; i >= 0; i--) {
					if (caracteres[i] != Constantes.CHAR_ZERO) {
						caracteres[i] = Constantes.CHAR_ZERO;
						break;
					}
				}
				retorno = String.valueOf(caracteres);
			}
			return retorno;
		} catch (Exception e) {
			throw e;
		}
	}
}
