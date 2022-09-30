package co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast;

import java.sql.Timestamp;

/**
 * Creado el 26/09/2022 a las 11:59:49 p. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public class LocalDateCast implements DateCast {

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast.DateCast#cast(java.sql.Timestamp)
	 */
	@Override
	public Object cast(Timestamp o) {
		return o.toLocalDateTime().toLocalDate();
	}

}
