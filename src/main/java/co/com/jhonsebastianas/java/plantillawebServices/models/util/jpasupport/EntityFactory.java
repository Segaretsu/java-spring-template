package co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;

import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;

import co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast.DateCast;
import co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast.LocalDateCast;
import co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast.LocalDateTimeCast;
import co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast.UtilDateCast;
import co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.datecast.ZonedDateTimeCast;
import co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.numbercast.DoubleCast;
import co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.numbercast.FloatCast;
import co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.numbercast.IntegerCast;
import co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.numbercast.LongCast;
import co.com.jhonsebastianas.java.plantillawebServices.models.util.jpasupport.numbercast.NumberCast;

/**
 * Creado el 26/09/2022 a las 11:32:38 p. m. <br>
 *
 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas.</a></br>
 */
public class EntityFactory {
	
	/** Consulta crea con los parámetros */
	private final Query query;
	
	/** Mapa con los tipos de números compatibles con EntityFactory */
	private static final Map<Class<?>, NumberCast> numberCast = new HashMap<>();
	
	/** Mapa con los tipos de fechas compatibles con EntityFactory */
	private static final Map<Class<?>, DateCast> dateCast = new HashMap<>();

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityFactory.class.getSimpleName());

	static {
		numberCast.put(Long.class, new LongCast());
		numberCast.put(Integer.class, new IntegerCast());
		numberCast.put(Double.class, new DoubleCast());
		numberCast.put(Float.class, new FloatCast());
		dateCast.put(Date.class, new UtilDateCast());
		dateCast.put(LocalDate.class, new LocalDateCast());
		dateCast.put(LocalDateTime.class, new LocalDateTimeCast());
		dateCast.put(ZonedDateTime.class, new ZonedDateTimeCast());
	}

	/**
	 * Crea una instancia de EntityFactory con una consulta inmodificable.
	 * @param query consulta con los parámetros
	 * @since 3.0.0
	 */
	public EntityFactory(final Query query) {
		Objects.requireNonNull(query, "El query no puede ser nulo");
		this.query = query;
	}
	
	/**
	 * Ejecuta una consulta y mapea el resultado en un DTO.
	 * Retorna un único resultado, en caso que la consulta devuelva más 
	 * de un registro o ningún registro se lanzan las excepciones de {@code Query} <br>
	 * Creado el 27/09/2022 a las 12:27:21 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param clazz clase a la que se quiere transformar el resultado de la consulta
	 * @return instancia de la clase
	 * 
	 * @since 3.0.0
     * @throws NonUniqueResultException si hay más de un resultado
     * @throws IllegalStateException si se llama para una instrucción UPDATE o 
     * 		   DELETE del lenguaje de consulta de persistencia de Java
     * @throws QueryTimeoutException si la ejecución de la consulta supera el 
     * 		   valor del tiempo de espera de la consulta y solo se revierte la 
     * 		   instrucción
     * @throws TransactionRequiredException si se ha establecido un modo de 
     * 		   bloqueo distinto de {@code NONE} y no hay transacción o el 
     * 		   contexto de persistencia no se ha unido a la transacción
     * @throws PessimisticLockException si el bloqueo pesimista falla y 
     * 		   la transacción se revierte
     * @throws LockTimeoutException si el bloqueo pesimista falla y solo 
     * 		   se revertirá la instrucción
     * @throws PersistenceException si la ejecución de la consulta supera el 
     * 		   valor de tiempo de espera de la consulta y la transacción se retrotrae
	 */
	public <T> Optional<T> reflect(final Class<T> clazz) {
		return Optional.ofNullable(transform(query, clazz));
	}
	
	/**
	 * Ejecuta una consulta y mapea el resultado en un DTO. <br>
	 * Creado el 27/09/2022 a las 12:32:16 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param clazz clase a la que se quiere transformar el resultado de la consulta
	 * @return instancia de la clase
	 * 
	 * @since 3.0.0
	 * @throws IllegalStateException si se llama para una instrucción UPDATE o 
     * 		   DELETE del lenguaje de consulta de persistencia de Java
     * @throws QueryTimeoutException si la ejecución de la consulta supera el 
     * 		   valor del tiempo de espera de la consulta y solo se revierte la instrucción
     * @throws TransactionRequiredException si se ha establecido un modo de 
     * 		   bloqueo distinto de {@code NONE} y no hay transacción o el 
     * 		   contexto de persistencia no se ha unido a la transacción
     * @throws PessimisticLockException si el bloqueo pesimista falla y 
     * 		   la transacción se revierte
     * @throws LockTimeoutException si el bloqueo pesimista falla y solo 
     * 		   se revertirá la instrucción
     * @throws PersistenceException si la ejecución de la consulta supera el 
     * 		   valor de tiempo de espera de la consulta y la transacción se retrotrae
	 */
	public <T> List<T> reflectList(final Class<T> clazz) {
		return transformList(query, clazz);
	}
	
	/**
	 * Ejecuta una consulta y mapea el resultado en un DTO.
	 * Retorna un único resultado, en caso que la consulta devuelva más 
	 * de un registro o ningún registro se lanzan las excepciones de {@code Query} <br>
	 * Creado el 27/09/2022 a las 12:34:02 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param query después de asignarle los parámetros
	 * @param clazz clase a la que se quiere transformar el resultado de la consulta
	 * @return instancia de la clase
	 * 
	 * @since 2.1.0
     * @throws IllegalStateException si se llama para una instrucción UPDATE o 
     * 		   DELETE del lenguaje de consulta de persistencia de Java
     * @throws QueryTimeoutException si la ejecución de la consulta supera el 
     * 		   valor del tiempo de espera de la consulta y solo se revierte la 
     * 		   instrucción
     * @throws TransactionRequiredException si se ha establecido un modo de 
     * 		   bloqueo distinto de {@code NONE} y no hay transacción o el 
     * 		   contexto de persistencia no se ha unido a la transacción
     * @throws PessimisticLockException si el bloqueo pesimista falla y 
     * 		   la transacción se revierte
     * @throws LockTimeoutException si el bloqueo pesimista falla y solo 
     * 		   se revertirá la instrucción
     * @throws PersistenceException si la ejecución de la consulta supera el 
     * 		   valor de tiempo de espera de la consulta y la transacción se retrotrae
	 */
	@SuppressWarnings("unchecked")
	private static <T> T transform(final Query query, final Class<T> clazz) {
		try {
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			Map<DatabaseField, ?> map = (Map<DatabaseField, ?>) query.getSingleResult();
			return mapToDTO(map, clazz);
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Ejecuta una consulta y mapea el resultado en un DTO. <br>
	 * Creado el 27/09/2022 a las 12:35:15 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param query después de asignarle los parámetros
	 * @param clazz clase a la que se quiere transformar el resultado de la consulta
	 * @return instancia de la clase
	 * 
	 * @since 2.0.0
	 * @throws IllegalStateException si se llama para una instrucción UPDATE o 
     * 		   DELETE del lenguaje de consulta de persistencia de Java
     * @throws QueryTimeoutException si la ejecución de la consulta supera el 
     * 		   valor del tiempo de espera de la consulta y solo se revierte la instrucción
     * @throws TransactionRequiredException si se ha establecido un modo de 
     * 		   bloqueo distinto de {@code NONE} y no hay transacción o el 
     * 		   contexto de persistencia no se ha unido a la transacción
     * @throws PessimisticLockException si el bloqueo pesimista falla y 
     * 		   la transacción se revierte
     * @throws LockTimeoutException si el bloqueo pesimista falla y solo 
     * 		   se revertirá la instrucción
     * @throws PersistenceException si la ejecución de la consulta supera el 
     * 		   valor de tiempo de espera de la consulta y la transacción se retrotrae
	 */
	@SuppressWarnings("unchecked")
	private static <T> List<T> transformList(final Query query, final Class<T> clazz) {
		List<T> response = new ArrayList<>();
		query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		List<Map<DatabaseField, ?>> res = query.getResultList();
		res.forEach(map -> response.add(mapToDTO(map, clazz)));
		return response;
	}
	
	/**
	 * Transforma un mapa retorando por el Query en una instancia de 
	 * la clase dada por parámetro <br>
	 * Creado el 27/09/2022 a las 12:36:13 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param map mapa con el resultado de la consulta
	 * @param clazz clase a la que se va a mapear
	 * @return instancia con los valores del mapa
	 * 
	 * @since 2.0.0
	 */
	private static <T> T mapToDTO(Map<DatabaseField, ?> map, final Class<T> clazz) {
		try {
			if (map.size() == 1 && isSimpleObject(clazz)) {
				return mapSingleObject(map.values().iterator().next(), clazz);
			}
			T instance = clazz.newInstance();
			List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
			map.entrySet().stream()
					.filter(field -> obtenerCampo(field.getKey().getName(), fields) != null)
					.forEach(i -> {
						Field field = obtenerCampo(i.getKey().getName(), fields);
						setValue(field, i.getValue(), instance);
					});
			return instance;
		} catch (InstantiationException | IllegalAccessException e) {
			LOGGER.error("Error creando una instancia de " + clazz.getName(), e);
		}
		return null;
	}

	private static boolean isSimpleObject(Class<?> clazz) {
		return clazz == String.class || Number.class.isAssignableFrom(clazz) 
				|| clazz == Date.class || clazz == LocalDateTime.class
				|| clazz == LocalDate.class;
	}

	/**
	 * Moldea objetos sencillos de un mapa con una sola llave a objetos como 
	 * String, Long, Integer, Double y Float. <br>
	 * Creado el 27/09/2022 a las 12:37:11 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param o objeto
	 * @param clazz clase final
	 * @return objeto instancia de la clase final
	 * 
	 * @since 3.1.0
	 */
	@SuppressWarnings("unchecked")
	private static <T> T mapSingleObject(Object o, Class<T> clazz) {
		return (T) castParam(o, clazz);
	}

	/**
	 * Asigna el valor al atributo de la instancia <br>
	 * Creado el 27/09/2022 a las 12:38:11 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param field
	 * @param o
	 * @param instance
	 * 
	 * @since 2.0.0
	 */
	private static <T> void setValue(Field field, Object o, T instance) {
		try {
			boolean isAccessible = field.isAccessible();
			field.setAccessible(true);
			field.set(instance, castParam(o, field.getType()));
			field.setAccessible(isAccessible);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LOGGER.error("Error seteando valor " + o + " en el campo " + field.getName(), e);
		}
	}

	/**
	 * Busca coincidencias entre el nombre de la columna y el nombre del atributo.
	 * Independiente de si está en {@code camelCase} o en {@code SNAKE_CASE} <br>
	 * Creado el 27/09/2022 a las 12:38:42 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param databaseField nombre de la columna
	 * @param fields lista con los campos de la clase
	 * @return el campo de la clase que coincide, de lo contrario {@code null}
	 *
	 * @since 2.0.0
	 */
	private static Field obtenerCampo(String databaseField, List<Field> fields) {
		return fields.stream().filter(field -> field.getName().equalsIgnoreCase(calcularNombreCampo(databaseField)))
				.findFirst()
				.orElse(null);
	}
	
	/**
	 * Valida si el nombre de la columna está en {@code SNAKE_CASE} y la transforma a
	 * {@code camelCase} y valida si tienen el mismo valor <br>
	 * Creado el 27/09/2022 a las 12:39:23 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param queryName nombre de la columna de la consulta
	 * @return nombre nombre de la columna transformado
	 * 
	 * @since 2.0.0
	 */
	private static String calcularNombreCampo(final String queryName) {
		if (queryName.contains("_")) {
			return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, queryName);
		}
		return queryName;
	}
	
	/**
	 * Moldea un objeto a la clase dada <br>
	 * Creado el 27/09/2022 a las 12:40:05 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param o objeto
	 * @param c clase a moldear
	 * @return instancia de la clase con el valor del objeto
	 * 
	 * @since 2.0.0
	 */
	private static Object castParam(Object o, Class<?> c) {
		try {
			if (o == null) {
				return null;
			} else if (Number.class.isAssignableFrom(c)) {
				// Casos especiales para moldear los números
				return castNumber(o, c);
			} else if (o.getClass() == Timestamp.class) {
				return castDate(o, c);
			} else if (c == String.class && !(o instanceof String)) {
				return o.toString();
			} else {
				return c.cast(o);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Moldea los números a la clase enviada por parámetro <br>
	 * Creado el 27/09/2022 a las 12:40:40 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param o objeto con el número
	 * @param clazz clase a la que debe ser moldeada
	 * @return número moldeado
	 */
	private static Number castNumber(Object o, Class<?> clazz) {
		return numberCast.get(clazz).cast(o);
	}
	
	/**
	 * Moldea las fechas a la clase enviada por parámetro <br>
	 * Creado el 27/09/2022 a las 12:41:09 a. m. <br>
	 *
	 * @author <a href="https://www.jhonsebastianas.com/">jhonsebastianas</a></br>
	 * @param o objeto con la fecha
	 * @param clazz clase a la que debe ser moldeada
	 * @return fecha moldeada
	 */
	private static Object castDate(Object o, Class<?> clazz) {
		if (dateCast.containsKey(clazz)) {
			return dateCast.get(clazz).cast((Timestamp) o);
		}
		return clazz.cast(o);
	}
}

