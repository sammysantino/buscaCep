package br.com.labs.buscacep.rest;

import javax.inject.Inject;
import lombok.Getter;
import org.slf4j.Logger;

public class BaseRest {

	@Inject
	@Getter private transient Logger log;

}