package br.com.labs.buscacep.rest.cep;

import br.com.labs.buscacep.model.Endereco;
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
public class InsereEnderecoEnvio implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "login")
	@Getter @Setter private String login;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "senha")
	@Getter @Setter private String senha;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "endereco")
	@Getter @Setter private Endereco endereco;

	public InsereEnderecoEnvio() {
		
	}
}