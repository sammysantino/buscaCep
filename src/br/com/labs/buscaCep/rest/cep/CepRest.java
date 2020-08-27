package br.com.labs.buscaCep.rest.cep;

import br.com.labs.buscaCep.rest.BaseRest;
import br.com.labs.buscaCep.rest.ECodigoRetorno;
import br.com.labs.buscaCep.servico.EnderecoServico;
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
	private EnderecoServico cepServico;
	
	@POST
	@Path("buscar")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buscar(BuscaCepEnvio buscaCepEnvio, @Context HttpServletRequest request) {
		try {
			getLog().info("INICIO BUSCAR CEP ");
//			System.out.println(Util.convertoToJson(vendaIntegracao));
//			System.out.println(Util.objectToJson(vendaIntegracao));
//			System.out.println(Util.objectToJsonComChave(vendaIntegracao));
			
			BuscaCepRetorno retorno = cepServico.buscarPorCep(buscaCepEnvio);
			
			getLog().info("FIM BUSCAR CEP ");
			return Response.ok(retorno).build();
		} catch (Exception ec) {
			ec.printStackTrace();
			return Response.ok(new BuscaCepRetorno(ECodigoRetorno.ERRO.getDescricao(), ec.getMessage())).build();
		}
	}
	
	
	@POST
	@Path("inserir")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(InsereEnderecoEnvio insereCepEnvio, @Context HttpServletRequest request) {
		try {
			getLog().info("INICIO INSERIR CEP ");
			
			InsereEnderecoRetorno retorno = cepServico.inserir(insereCepEnvio);
			
			getLog().info("FIM INSERIR CEP ");
			return Response.ok(retorno).build();
		} catch (Exception ec) {
			ec.printStackTrace();
			return Response.ok(new BuscaCepRetorno(ECodigoRetorno.ERRO.getDescricao(), ec.getMessage())).build();
		}
	}
}
