package br.com.labs.buscacep.rest.endereco;

import br.com.labs.buscacep.entidade.Endereco;
import br.com.labs.buscacep.rest.BaseEnvio;
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
public class InsereEnderecoEnvio extends BaseEnvio implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "endereco")
	@Getter @Setter private Endereco endereco;

	public InsereEnderecoEnvio(String login, String senha) {
		super(login, senha);
	}
}