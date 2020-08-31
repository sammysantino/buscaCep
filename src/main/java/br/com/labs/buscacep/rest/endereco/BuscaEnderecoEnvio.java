package br.com.labs.buscacep.rest.endereco;

import br.com.labs.buscacep.rest.BaseEnvio;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BuscaEnderecoEnvio extends BaseEnvio implements Serializable {

	private static final long serialVersionUID = 1L;

	public BuscaEnderecoEnvio(String login, String senha) {
		super(login, senha);
	}
}