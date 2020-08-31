package br.com.labs.buscacep.entidade;

import br.com.labs.buscacep.util.Constantes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@EqualsAndHashCode(of = {"login"})
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "endereco", schema = "public")
public class Autorizacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "login")
	@Id
	@Column(name = "login", length = Constantes.TRINTA)
	@Getter @Setter private String login;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "senha")
	@Column(name = "login", length = Constantes.QUINZE, nullable = false)
	@Getter @Setter private String senha;
	
	@JsonInclude(Include.NON_EMPTY)
	@XmlElement(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	@Getter @Setter private LocalDateTime dataCadastro;
	
	public Autorizacao(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	@PrePersist
	private void prePersist() {
		dataCadastro = LocalDateTime.now();
	}
	
}
