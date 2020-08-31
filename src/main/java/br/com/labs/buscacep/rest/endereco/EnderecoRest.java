package br.com.labs.buscacep.rest.endereco;

import br.com.labs.buscacep.rest.BaseRest;
import br.com.labs.buscacep.rest.ECodigoRetorno;
import br.com.labs.buscacep.servico.EnderecoServico;
import br.com.labs.buscacep.util.Constantes;
import br.com.labs.buscacep.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/endereco")
public class EnderecoRest extends BaseRest implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private EnderecoServico enderecoServico;
	
	@POST
	@Path("inserir")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(InsereEnderecoEnvio insereCepEnvio, @Context HttpServletRequest request) {
		InsereEnderecoRetorno retorno = null;
		try {
			getLog().info("INICIO INSERIR ENDERECO " + Util.getJson(insereCepEnvio));
			retorno = enderecoServico.inserir(insereCepEnvio);
			getLog().info("FIM INSERIR ENDERECO " + Util.getJson(retorno));
		} catch (Exception ec) {
			retorno = new InsereEnderecoRetorno(ECodigoRetorno.ERRO.getCodigo(), Constantes.MENSAGEM_ERRO_PADRAO);
			getLog().error("ERRO AO INSERIR ENDERECO " + ec.getMessage());
		}
		return Response.ok(retorno).build();
	}
	
	@GET
	@Path("buscarTodos")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buscar(BuscaEnderecoEnvio buscaEnderecoEnvio, @Context HttpServletRequest request) {
		BuscaEnderecoRetorno retorno = null;
		try {
			getLog().info("INICIO BUSCAR TODOS ENDERECOS " + Util.getJson(buscaEnderecoEnvio));
			retorno = enderecoServico.obterTodos(buscaEnderecoEnvio);
			getLog().info("FIM BUSCAR TODOS ENDERECOS " + Util.getJson(retorno));
		} catch (Exception ec) {
			retorno = new BuscaEnderecoRetorno(ECodigoRetorno.ERRO.getCodigo(), Constantes.MENSAGEM_ERRO_PADRAO);
			getLog().error("ERRO AO BUSCAR TODOS ENDERECOS " + ec.getMessage());
		}
		return Response.ok(retorno).build();
	}
	
	@POST
	@Path("buscarPorCep")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buscar(BuscaCepEnvio buscaCepEnvio, @Context HttpServletRequest request) {
		BuscaCepRetorno retorno = null;
		try {
			getLog().info("INICIO BUSCAR CEP " + Util.getJson(buscaCepEnvio));
			retorno = enderecoServico.buscarPorCep(buscaCepEnvio);
			getLog().info("FIM BUSCAR CEP " + Util.getJson(retorno));
		} catch (Exception ec) {
			retorno = new BuscaCepRetorno(ECodigoRetorno.ERRO.getCodigo(), Constantes.MENSAGEM_ERRO_PADRAO);
			getLog().error("ERRO AO BUSCAR CEP " + ec.getMessage());
		}
		return Response.ok(retorno).build();
	}

}