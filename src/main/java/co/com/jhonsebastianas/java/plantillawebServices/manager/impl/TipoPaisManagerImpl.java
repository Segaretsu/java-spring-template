/**
 * 
 */
package co.com.jhonsebastianas.java.plantillawebServices.manager.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.jhonsebastianas.java.plantillawebServices.dao.TipoPaisDao;
import co.com.jhonsebastianas.java.plantillawebServices.manager.TipoPaisManager;
import co.com.jhonsebastianas.java.plantillawebServices.models.dto.TipoPaisInDTO;
import co.com.jhonsebastianas.java.plantillawebServices.models.entity.TipoPaisEntity;

/**
 * Clase encargada de la capa de negocio de tipo_pais<br>
 * Creado el 26/08/2022 a las 2:21:58 p. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
@Service
public class TipoPaisManagerImpl implements TipoPaisManager {
	
	@Autowired
	private TipoPaisDao tipoPaisDao;

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.manager.TipoPaisManager#registrarPais(co.com.jhonsebastianas.java.plantillawebServices.models.dto.TipoPaisInDTO)
	 */
	@Override
	@Transactional
	public Response registrarPais(TipoPaisInDTO paisRegistrar) {
		TipoPaisEntity tipoPais = new TipoPaisEntity();
		tipoPais.setNombre(paisRegistrar.getNombre());
		tipoPais.setCodigoPais(paisRegistrar.getCodigoPais());
		this.tipoPaisDao.insert(tipoPais);
		return Response.ok(tipoPais).build();
	}

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.manager.TipoPaisManager#getAllTipoPais()
	 */
	@Override
	public List<TipoPaisEntity> getAllTipoPais() {
		return this.tipoPaisDao.findAllPaises();
	}

}
