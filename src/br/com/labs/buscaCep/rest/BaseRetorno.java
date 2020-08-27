package br.com.labs.buscaCep.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseRetorno implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "codigo_retorno")
	@Getter @Setter private String codigoRetorno;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "mensagem_retorno")
	@Getter @Setter private String mensagemRetorno;

	public BaseRetorno() {
		
	}
	
	public BaseRetorno(String codigoRetorno, String mensagemRetorno) {
		this.codigoRetorno = codigoRetorno;
		this.mensagemRetorno = mensagemRetorno;
	}
}