package co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.numbercast;

/**
 * Creado el 26/09/2022 a las 11:33:40 p. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public class DoubleCast implements NumberCast {

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.numbercast.NumberCast#cast(java.lang.Object)
	 */
	@Override
	public Number cast(Object o) {
		if (o instanceof String) {
			return Double.parseDouble((String) o);
		}
		return ((Number) o).doubleValue();
	}

}
