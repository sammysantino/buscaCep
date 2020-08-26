package br.com.labs.buscaCep.log;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import javax.ejb.AccessTimeout;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.apache.log4j.Logger;

@Singleton
public class ProdutorLogger implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@AccessTimeout(value = 60, unit = TimeUnit.SECONDS)
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
