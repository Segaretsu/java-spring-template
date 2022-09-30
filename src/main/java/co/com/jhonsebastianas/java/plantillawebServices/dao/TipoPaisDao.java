/**
 * 
 */
package co.com.jhonsebastianas.java.plantillawebServices.dao;

import java.util.List;

import co.com.jhonsebastianas.java.plantillawebServices.models.dto.TipoPaisOutDTO;
import co.com.jhonsebastianas.java.plantillawebServices.models.entity.TipoPaisEntity;

/**
 * Interface encargada de definir los métodos para la capa de datos de pais.
 * <br>
 * Creado el 24/08/2022 a las 9:29:09 a. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public interface TipoPaisDao {

	/**
	 * Método encargado de registrar un pais <br>
	 * Creado el 24/08/2022 a las 9:30:33 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
	 * @param paisEntity
	 */
	void insert(TipoPaisEntity paisEntity);

	/**
	 * Método encargado de actualizar un pais <br>
	 * Creado el 24/08/2022 a las 9:30:45 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
	 * @param paisEntity
	 */
	void update(TipoPaisEntity paisEntity);

	/**
	 * Método encargado de eliminar un pais <br>
	 * Creado el 24/08/2022 a las 9:30:53 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
	 * @param paisEntity
	 */
	void delete(TipoPaisEntity paisEntity);

	/**
	 * Método encargado de encontrar un pais por su id <br>
	 * Creado el 24/08/2022 a las 9:31:00 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
	 * @param idTipoPais
	 * @return
	 */
	TipoPaisEntity findByPk(Long idTipoPais);
	
	/**
	 * Método encargado de traer todos los paises. <br>
	 * Creado el 26/08/2022 a las 2:25:19 p. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @return
	 */
	List<TipoPaisEntity> findAllPaises();
	
	/**
	 * Método encargado de retornar el tipoPais DTO buscado por codigoPais <br>
	 * Creado el 27/09/2022 a las 12:45:22 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param codigoPais
	 * @return
	 */
	TipoPaisOutDTO findByCodigoPais(String codigoPais);

}
