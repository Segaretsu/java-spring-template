package co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.numbercast;

/**
 * Creado el 26/09/2022 a las 11:37:18 p. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public class FloatCast implements NumberCast {

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.numbercast.NumberCast#cast(java.lang.Object)
	 */
	@Override
	public Number cast(Object o) {
		if (o instanceof String) {
			return Float.parseFloat((String) o);
		}
		return ((Number) o).floatValue();
	}

}
