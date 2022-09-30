package co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.numbercast;

/**
 * Creado el 26/09/2022 a las 11:35:16 p. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public interface NumberCast {

	/**
	 * Transforma un número que implemente {@code java.lang.Number} en la 
	 * referencia de la implementación de {@code NumberCast}<br>
	 * 
	 * Creado el 26/09/2022 a las 11:35:16 p. m. <br>
	 * 
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
	 * @param o objeto a ser casteado
	 * @return número
	 */
	Number cast(Object o);
}

