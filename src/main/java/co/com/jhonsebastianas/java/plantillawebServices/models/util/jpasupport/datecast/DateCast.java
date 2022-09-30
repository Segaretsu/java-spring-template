/**
 * 
 */
package co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast;

import java.sql.Timestamp;

/**
 * Casteo de fechas. <br>
 * Creado el 26/09/2022 a las 11:51:50 p. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public interface DateCast {

	/**
	 * Transforma una fecha en la referencia de la implementación de
	 * {@link co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast.DateCast}<br>
	 * Creado el 17/04/2019 a las 10:23:11 a.m. <br>
	 * 
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
	 * @param o objeto a ser casteado
	 * @return fecha
	 */
	Object cast(Timestamp o);
}
