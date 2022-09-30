package co.com.jhonsebastianas.java.plantillawebServices.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import co.com.jhonsebastianas.java.plantillawebServices.conf.JPAConfigMultiple;
import co.com.jhonsebastianas.java.plantillawebServices.dao.TipoPaisDao;
import co.com.jhonsebastianas.java.plantillawebServices.models.dto.TipoPaisOutDTO;
import co.com.jhonsebastianas.java.plantillawebServices.models.entity.TipoPaisEntity;
import co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.EntityFactory;

/**
 * Clase encargada de definir los métodos para la capa de datos de pais. <br>
 * Creado el 24/08/2022 a las 9:32:49 a. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
@Repository
public class TipoPaisDaoImpl implements TipoPaisDao {
	
	@PersistenceContext()
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.dao.PaisDao#insert(co.com.jhonsebastianas.java.plantillawebServices.models.entity.PaisEntity)
	 */
	@Override
	public void insert(TipoPaisEntity paisEntity) {
		this.entityManager.persist(paisEntity);
	}

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.dao.PaisDao#update(co.com.jhonsebastianas.java.plantillawebServices.models.entity.PaisEntity)
	 */
	@Override
	public void update(TipoPaisEntity paisEntity) {
		this.entityManager.merge(null);
		this.entityManager.flush();
	}

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.dao.PaisDao#delete(co.com.jhonsebastianas.java.plantillawebServices.models.entity.PaisEntity)
	 */
	@Override
	public void delete(TipoPaisEntity paisEntity) {
		this.entityManager.remove(paisEntity);
	}

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.dao.PaisDao#findByPk(java.lang.Long)
	 */
	@Override
	public TipoPaisEntity findByPk(Long idTipoPais) {
		return this.entityManager.find(TipoPaisEntity.class, idTipoPais);
	}

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.dao.TipoPaisDao#findAllPaises()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoPaisEntity> findAllPaises() {
		Query query = entityManager.createNativeQuery("SELECT * FROM TIPO_PAIS", TipoPaisEntity.class);
		return (List<TipoPaisEntity>) query.getResultList();
	}

	/* (non-Javadoc)
	 * @see co.com.jhonsebastianas.java.plantillawebServices.dao.TipoPaisDao#findByCodigoPais(java.lang.String)
	 */
	@Override
	public TipoPaisOutDTO findByCodigoPais(String codigoPais) {
		Query query = entityManager
				.createNativeQuery(
						"SELECT tp.nombre, tp.codigo_pais " +
						"FROM TIPO_PAIS tp " + 
						"WHERE tp.CODIGO_PAIS = :codigoPais ");
		query.setParameter("codigoPais", codigoPais);
		return new EntityFactory(query).reflect(TipoPaisOutDTO.class).orElse(null);
	}

}
