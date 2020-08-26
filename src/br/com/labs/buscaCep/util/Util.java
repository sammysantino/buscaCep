package br.com.labs.buscaCep.util;

public final class Util {

	private Util() {
		
	}
	
	public static String removerCaracteresNaoNumericos(String value) {
		if (value != null) {
			value = value.replaceAll("[^\\d]", "").trim();
			value = value.replaceAll("\\+", "");
		}
		return value;
	}

	public static boolean isEmpty(String value) {
		return value == null || value.replaceAll(" ", "").isEmpty();
	}
	
}
