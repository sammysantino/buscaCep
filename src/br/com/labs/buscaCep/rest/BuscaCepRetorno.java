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
public class BuscaCepRetorno implements Serializable {
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "codigo_retorno")
	@Getter @Setter private String codigoRetorno;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "mensagem_retorno")
	@Getter @Setter private String mensagemRetorno;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "data_processamento")
	@Getter @Setter private String dataProcessamento;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "rua")
	@Getter @Setter private String rua;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "bairro")
	@Getter @Setter private String bairro;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "cidade")
	@Getter @Setter private String cidade;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "estado")
	@Getter @Setter private String estado;

	public BuscaCepRetorno() {
		
	}
	
	public BuscaCepRetorno(String codigoRetorno, String mensagemRetorno) {
		this.codigoRetorno = codigoRetorno;
		this.mensagemRetorno = mensagemRetorno;
	}
}