package co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Creado el 27/09/2022 a las 12:02:48 a. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public class UtilDateCast implements DateCast {

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast.DateCast#cast(java.sql.Timestamp)
	 */
	@Override
	public Object cast(Timestamp o) {
		return new Date(o.getTime());
	}

}
