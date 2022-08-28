/**
 * 
 */
package co.com.jhonsebastianas.java.plantillawebServices.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Servicio encargado de exponer los servicios de la API.<br>
 * Creado el 24/08/2022 a las 1:24:30 a. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">Jhon Sebastian AS.</a></br>
 */
@Path("base")
@Api("base")
@Component
public class BaseService {
	
	@GET
	@Path("/base-get")
	@ApiOperation(value = "base-get", notes = "Retorna un string")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String findAllAlbums () {
		return "El servicio get fue consumido con exito";
	}

}
