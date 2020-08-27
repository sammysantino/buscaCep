package br.com.labs.buscaCep.rest.cep;

import br.com.labs.buscaCep.rest.BaseRest;
import br.com.labs.buscaCep.rest.ECodigoRetorno;
import br.com.labs.buscaCep.servico.EnderecoServico;
import br.com.labs.buscaCep.util.Constantes;
import br.com.labs.buscaCep.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cep")
public class CepRest extends BaseRest implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private EnderecoServico enderecoServico;
	
	@POST
	@Path("buscar")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buscar(BuscaCepEnvio buscaCepEnvio, @Context HttpServletRequest request) {
		try {
			getLog().info("INICIO BUSCAR CEP " + Util.obterJson(buscaCepEnvio));
			
			BuscaCepRetorno retorno = enderecoServico.buscarPorCep(buscaCepEnvio);
			
			getLog().info("FIM BUSCAR CEP " + Util.obterJson(retorno));
			return Response.ok(retorno).build();
		} catch (Exception ec) {
			ec.printStackTrace();
			getLog().info("ERRO AO BUSCAR CEP " + ec.getMessage());
			return Response.ok(new BuscaCepRetorno(ECodigoRetorno.ERRO.getDescricao(), ec.getMessage())).build();
		}
	}
	
	
	@POST
	@Path("inserir")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(InsereEnderecoEnvio insereCepEnvio, @Context HttpServletRequest request) {
		InsereEnderecoRetorno retorno = null;
		try {
			getLog().info("INICIO INSERIR CEP " + Util.obterJson(insereCepEnvio));
			retorno = enderecoServico.inserir(insereCepEnvio);
		} catch (Exception ec) {
			ec.printStackTrace();
			retorno = new InsereEnderecoRetorno(ECodigoRetorno.ERRO.getDescricao(), Constantes.REST_MENSAGEM_ERRO_PADRAO);
		}
		
		try {
			getLog().info("FIM INSERIR CEP " + Util.obterJson(retorno));
		} catch (Exception e) {
			getLog().info("FIM INSERIR CEP " + retorno.getMensagemRetorno());
		}
		
		
		return Response.ok(retorno).build();
	}
}
