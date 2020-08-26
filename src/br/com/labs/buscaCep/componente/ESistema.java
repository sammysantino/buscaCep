package br.com.labs.buscaCep.componente;

public enum ESistema {

	OMINI("nx"),
	SALESCALL("nx");

	private final String pastaCliente;

	ESistema(String pastaCliente) {
		this.pastaCliente = pastaCliente;
	}

	public String getPastaCliente() {
		return pastaCliente;
	}
}