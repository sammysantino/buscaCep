package br.com.labs.buscacep.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of = {"cep", "rua", "bairro", "cidade", "estado"})
@NoArgsConstructor
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
	
	@XmlTransient
	public static class EnderecoBuilder {
		Endereco endereco;
		
		public EnderecoBuilder() {
			endereco = new Endereco();
		}
			
		public EnderecoBuilder cep(String cep) {
			endereco.setCep(cep);
			return this;
		}
		
		public EnderecoBuilder rua(String rua) {
			endereco.setRua(rua);
			return this;
		}
		
		public EnderecoBuilder bairro(String bairro) {
			endereco.setBairro(bairro);
			return this;
		}
		
		public EnderecoBuilder cidade(String cidade) {
			endereco.setCidade(cidade);
			return this;
		}
		
		public EnderecoBuilder estado(String estado) {
			endereco.setEstado(estado);
			return this;
		}
		
		public Endereco build() {
			return endereco;
		}
	}
	
}
