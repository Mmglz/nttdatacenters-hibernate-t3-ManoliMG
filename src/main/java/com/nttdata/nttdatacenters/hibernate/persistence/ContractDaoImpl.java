package com.nttdata.nttdatacenters.hibernate.persistence;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

/**
 * Implementación del DAO de Contract
 * 
 * @author manoli
 *
 */
public class ContractDaoImpl extends CommonDaoImpl<Contract> implements ContractDaoI {

	/** Sesión de conexión a la BBDD. */
	private Session session;

	/**
	 * Método constructor.
	 */
	public ContractDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	@Override
	public List<Contract> searchByCustomerId(Long customer) {

		// Consulta.
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Contract> cquery = cb.createQuery(Contract.class);
		final Root<Contract> rootContract = cquery.from(Contract.class);
		final Join<Contract, Customer> conJoinCus = rootContract.join("customer");

		// Where
		final Predicate pre1 = cb.le(conJoinCus.<Long>get("customerId"), customer);

		// Consulta.
		cquery.select(rootContract).where(cb.and(pre1));

		// Ejecución de la consulta.
		return session.createQuery(cquery).getResultList();
	}

	@Override
	public List<Contract> searchBySurnameAndMonthlyPrice(String firstSurname, double monthlyPrice) {

		// Consulta.
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Contract> cquery = cb.createQuery(Contract.class);
		final Root<Contract> rootContract = cquery.from(Contract.class);
		final Join<Contract, Customer> conJoinCus = rootContract.join("customer");

		// Where.
		final Predicate pre1 = cb.lessThan(rootContract.<Double>get("monthlyPrice"), monthlyPrice);
		final Predicate pre2 = cb.like(conJoinCus.<String>get("firstSurname"), firstSurname);

		// Consulta.
		cquery.select(rootContract).where(cb.and(pre1, pre2));

		// Ordenación ascendente (menor a mayor) del precio mensual.
		cquery.orderBy(cb.asc(rootContract.get("monthlyPrice")));

		// Ejecución de la consulta.
		return session.createQuery(cquery).getResultList();
	}
}
