package br.com.labs.buscaCep.pojo;

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
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "cep")
	@Getter @Setter private String cep;
	
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
	
}
