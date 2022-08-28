package co.com.jhonsebastianas.java.plantillawebServices.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad de pais <br>
 * Creado el 24/08/2022 a las 1:37:52 a. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">Jhon Sebastian
 *         AS.</a></br>
 */
@Entity(name = "TipoPaisEntity")
@Table(name = "TIPO_PAIS")
public class TipoPaisEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_TIPO_PAIS")
	private Long idTipoPais;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "CODIGO_PAIS")
	private String codigoPais;

	/**
	 * @return the idPais
	 */
	public Long getIdTipoPais() {
		return idTipoPais;
	}

	/**
	 * @param idPais the idPais to set
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
