package br.com.labs.buscacep.rest.cep;

import br.com.labs.buscacep.entidade.CepInformacao;
import br.com.labs.buscacep.rest.BaseRetorno;
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
public class BuscaCepRetorno extends BaseRetorno implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "cep")
	@Getter @Setter private CepInformacao cep;

	public BuscaCepRetorno(Integer codigoRetorno, String mensagemRetorno) {
		super(codigoRetorno, mensagemRetorno);
	}
	
}