package br.com.labs.buscaCep.rest.cep;

import br.com.labs.buscaCep.rest.BaseRetorno;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InsereEnderecoRetorno extends BaseRetorno implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public InsereEnderecoRetorno() {
		
	}
	
	public InsereEnderecoRetorno(String codigoRetorno, String mensagemRetorno) {
		super(codigoRetorno, mensagemRetorno);
	}
}