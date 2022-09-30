package co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast;

import java.sql.Timestamp;
import java.time.ZoneId;

/**
 * Creado el 27/09/2022 a las 12:04:20 a. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public class ZonedDateTimeCast implements DateCast {

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast.DateCast#cast(java.sql.Timestamp)
	 */
	@Override
	public Object cast(Timestamp o) {
		return o.toInstant().atZone(ZoneId.systemDefault());
	}

}
