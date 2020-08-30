package br.com.labs.buscacep.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of = {"login", "senha"})
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Autorizacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "login")
	@Getter @Setter private String login;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "senha")
	@Getter @Setter private String senha;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "data_cadastro")
	@Getter @Setter private LocalDateTime dataCadastro;
	
	public Autorizacao(String login, String senha, LocalDateTime dataCadastro) {
		this.login = login;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
	}
	
}
