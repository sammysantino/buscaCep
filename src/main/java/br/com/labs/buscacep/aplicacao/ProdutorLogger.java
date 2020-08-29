package br.com.labs.buscacep.aplicacao;

import java.io.Serializable;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ProdutorLogger implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	public Logger getLogger(final InjectionPoint ip) {
		return LoggerFactory.getLogger(ip.getMember().getClass());
	}
}
