package co.com.jhonsebastianas.java.plantillawebServices.models.dto;

import java.io.Serializable;

/**
 * Creado el 27/09/2022 a las 12:43:49 a. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public class TipoPaisOutDTO implements Serializable {

	private static final long serialVersionUID = 1581303296269199286L;
	private Long idTipoPais;
	private String nombre;
	private String codigoPais;

	/**
	 * @return the idTipoPais
	 */
	public Long getIdTipoPais() {
		return idTipoPais;
	}

	/**
	 * @param idTipoPais the idTipoPais to set
	 */
	public void setIdTipoPais(Long idTipoPais) {
		this.idTipoPais = idTipoPais;
	}

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
