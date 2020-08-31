package br.com.labs.buscacep.rest.endereco;

import br.com.labs.buscacep.entidade.Endereco;
import br.com.labs.buscacep.rest.BaseRetorno;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;
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
public class BuscaEnderecoRetorno extends BaseRetorno implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "enderecos")
	@Getter @Setter private List<Endereco> enderecos;

	public BuscaEnderecoRetorno(Integer codigoRetorno, String mensagemRetorno) {
		super(codigoRetorno, mensagemRetorno);
	}

	public BuscaEnderecoRetorno(Integer codigoRetorno, String mensagemRetorno, List<Endereco> enderecos) {
		this(codigoRetorno, mensagemRetorno);
		this.enderecos = enderecos;
	}
	
} 