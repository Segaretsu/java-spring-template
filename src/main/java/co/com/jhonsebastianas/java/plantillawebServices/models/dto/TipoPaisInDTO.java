package co.com.jhonsebastianas.java.plantillawebServices.models.dto;

import java.io.Serializable;

/**
 * TODO: descripción <br>
 * Creado el 24/08/2022 a las 1:53:23 p. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public class TipoPaisInDTO implements Serializable {

	private static final long serialVersionUID = -5552919183714852778L;
	private String nombre;
	private String codigoPais;

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

}
