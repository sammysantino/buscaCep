package br.com.labs.buscacep.entidade;

import br.com.labs.buscacep.util.Constantes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "endereco", schema = "public")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlTransient
	@Id
	@SequenceGenerator(name = "enderecoSeq", sequenceName = "endereco_id_seq", allocationSize = 1, schema = "public")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "enderecoSeq")
	private Long id;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "cep")
	@Column(name = "cep", length = Constantes.OITO, nullable = false, unique = true)
	@Getter @Setter private String cep;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "rua")
	@Column(name = "rua", length = Constantes.CENTO_CINQUENTA, nullable = false)
	@Getter @Setter private String rua;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "bairro")
	@Column(name = "bairro", length = Constantes.CENTO_CINQUENTA, nullable = false)
	@Getter @Setter private String bairro;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "cidade")
	@Column(name = "cidade", length = Constantes.CENTO_CINQUENTA, nullable = false)
	@Getter @Setter private String cidade;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "estado")
	@Column(name = "estado", length = Constantes.CENTO_CINQUENTA, nullable = false)
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
