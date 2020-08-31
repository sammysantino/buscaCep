package br.com.labs.buscacep.rest.endereco;

import br.com.labs.buscacep.rest.BaseRetorno;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InsereEnderecoRetorno extends BaseRetorno implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public InsereEnderecoRetorno(Integer codigoRetorno, String mensagemRetorno) {
		super(codigoRetorno, mensagemRetorno);
	}
	
}