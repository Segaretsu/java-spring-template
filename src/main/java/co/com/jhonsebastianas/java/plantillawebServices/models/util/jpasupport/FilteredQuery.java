/**
 * 
 */
package co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Creado el 27/09/2022 a las 12:05:33 a. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public class FilteredQuery {
	private FilteredQuery() {
		throw new AssertionError("¡No hay instancias para ti!");
	}

	/**
	 * Crea un {@code TypedQuery} filtrado con los parámetro enviados en el {@code map}.
	 * En el caso que uno de los parámetro no se reconozca será omitido.<br>
	 * La estructura del mapa es la siguiente: 
	 * <ul>
	 *   <li>key: nombre del atributo como está en en la entidad.</li>
	 *   <li>value: objeto con el valor a ser comparado.</li>
	 * </ul>
	 * Creado el 27/09/2022 a las 12:09:33 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param <E> clase de la entidad consultada
	 * @param clazz clazz clase de la entidad consultada
	 * @param entityManager parámetro para hacer el filtro
	 * @param queryParams parámetro para hacer el filtro
	 * @return consulta con los filtros aplicados
	 */
	public static <E> TypedQuery<E> createFilteredQuery(Class<E> clazz, EntityManager entityManager, Map<String, Object> queryParams) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> query = builder.createQuery(clazz);
		Root<E> from = query.from(clazz);
		query.select(from).where(filter(from, builder, queryParams));
		return entityManager.createQuery(query);
	}
	
	/**
	 * Convierte un {@code MultivaluedMap} de JAX-RS en un {@code Map}. 
	 * En el caso que hayan valores duplicados solo será tomado el primero.<br>
	 * Creado el 27/09/2022 a las 12:08:43 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param multivaluedMap mapa de JAX-RS
	 * @return mapa
	 */
	public static Map<String, Object> multivaluedMapToMap(MultivaluedMap<String, String> multivaluedMap) {
		return multivaluedMap.keySet().parallelStream()
				.collect(Collectors.toMap(Function.identity(), multivaluedMap::getFirst));
	}

	/**
	 * Aplica los filtros enviados a la consulta <br>
	 * Creado el 27/09/2022 a las 12:07:59 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param from referencia de la entidad
	 * @param builder objecto usado para construir la consulta
	 * @param queryParams parámetros para el filtro
	 * @return arreglo con los filtros
	 */
	private static <E> Predicate[] filter(Root<E> from, CriteriaBuilder builder, Map<String, Object> queryParams) {
		return queryParams.entrySet().parallelStream()
				.map(entry -> {
					try {
						return builder.equal(from.get(entry.getKey()), entry.getValue());
					} catch (IllegalArgumentException e) {
						return null;
					}
				})
				.filter(Objects::nonNull)
				.toArray(Predicate[]::new);
	}
}
