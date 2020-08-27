package br.com.labs.buscaCep.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Credencial implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "login")
	@Getter @Setter private String login;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "senha")
	@Getter @Setter private String senha;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "data_cadastro")
	@Getter @Setter private Calendar dataCadastro;
	
}
