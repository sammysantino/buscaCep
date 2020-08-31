package br.com.labs.buscacep.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseEnvio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "login")
	@Getter @Setter private String login;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "senha")
	@Getter @Setter private String senha;

	public BaseEnvio(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
}
