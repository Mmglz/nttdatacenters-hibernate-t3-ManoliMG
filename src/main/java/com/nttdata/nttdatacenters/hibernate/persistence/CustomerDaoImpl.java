package com.nttdata.nttdatacenters.hibernate.persistence;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 * Implementación del DAO de Customer
 * 
 * @author manoli
 *
 */
public class CustomerDaoImpl extends CommonDaoImpl<Customer> implements CustomerDaoI {

	/** Sesión de conexión a la BBDD. */
	private Session session;

	/**
	 * Método constructor.
	 */
	public CustomerDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	@Override
	public List<Customer> searchByNameAndSurnames(final String name, final String firstSurname,	final String secondSurname) {

		// Consulta.
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Customer> cquery = cb.createQuery(Customer.class);
		final Root<Customer> rootCustomer = cquery.from(Customer.class);

		// Where
		final Predicate pre1 = cb.like(rootCustomer.<String>get("name"), name);
		final Predicate pre2 = cb.like(rootCustomer.<String>get("firstSurname"), firstSurname);
		final Predicate pre3 = cb.like(rootCustomer.<String>get("secondSurname"), secondSurname);

		// Consulta.
		cquery.select(rootCustomer).where(cb.and(pre1, pre2, pre3));

		// Ejecución de la consulta.
		return session.createQuery(cquery).getResultList();
	}

	@Override
	public List<Customer> searchByNameAndCity(String name, String city) {

		// Consulta
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Customer> cquery = cb.createQuery(Customer.class);
		final Root<Customer> rootCus = cquery.from(Customer.class);

		// Where.
		final Predicate pre1 = cb.like(rootCus.<String>get("name"), name);
		final Predicate pre2 = cb.like(rootCus.<String>get("city"), city);

		// Consulta.
		cquery.select(rootCus).where(cb.and(pre1, pre2));

		// Ejecución de la consulta.
		return session.createQuery(cquery).getResultList();
	}
}
