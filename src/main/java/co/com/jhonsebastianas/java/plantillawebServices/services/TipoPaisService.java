/**
 * 
 */
package co.com.jhonsebastianas.java.plantillawebServices.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.jhonsebastianas.java.plantillawebServices.manager.TipoPaisManager;
import co.com.jhonsebastianas.java.plantillawebServices.models.dto.TipoPaisInDTO;
import co.com.jhonsebastianas.java.plantillawebServices.models.dto.TipoPaisOutDTO;
import co.com.jhonsebastianas.java.plantillawebServices.models.entity.TipoPaisEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * TODO: descripción <br>
 * Creado el 26/08/2022 a las 2:34:02 p. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
@Path("tipo-pais")
@Api("/tipo-pais")
@Component
public class TipoPaisService {
	
	@Autowired
	private TipoPaisManager tipoPaisManager;
	
	@POST
	@ApiOperation(value = "/", notes = "Registra un nuevo pais")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarPais (TipoPaisInDTO paisRegistrar) {
		return this.tipoPaisManager.registrarPais(paisRegistrar);
	}
	
	@GET
	@Path("/findAll")
	@ApiOperation(value = "/findAll", notes = "Retorna la lista de los paises registrados")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TipoPaisEntity> findAllAlbums () {
		return this.tipoPaisManager.getAllTipoPais();
	}
	
	@GET
	@Path("/findByCodigoPais/{codigoPais}")
	@ApiOperation(value = "/findAll", notes = "Retorna la lista de los paises registrados")
	@Produces(MediaType.APPLICATION_JSON)
	public TipoPaisOutDTO findByCodigoPais (@PathParam("codigoPais") String codigoPais) {
		return this.tipoPaisManager.findByCodigoPais(codigoPais);
	}

}
