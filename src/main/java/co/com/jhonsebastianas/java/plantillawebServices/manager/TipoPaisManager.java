package co.com.jhonsebastianas.java.plantillawebServices.manager;

import java.util.List;

import javax.ws.rs.core.Response;

import co.com.jhonsebastianas.java.plantillawebServices.models.dto.TipoPaisInDTO;
import co.com.jhonsebastianas.java.plantillawebServices.models.entity.TipoPaisEntity;

/**
 * Interface encargada de la capa de negocio de tipo pais. <br>
 * Creado el 24/08/2022 a las 1:49:24 p. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public interface TipoPaisManager {

	/**
	 * Método encargado de registrar un tipo pais. <br>
	 * Creado el 24/08/2022 a las 2:00:20 p. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param paisRegistrar
	 * @return
	 */
	Response registrarPais(TipoPaisInDTO paisRegistrar);

	/**
	 * Método encargado de retornar todos los paises. <br>
	 * Creado el 24/08/2022 a las 2:00:32 p. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @return
	 */
	List<TipoPaisEntity> getAllTipoPais();

}
