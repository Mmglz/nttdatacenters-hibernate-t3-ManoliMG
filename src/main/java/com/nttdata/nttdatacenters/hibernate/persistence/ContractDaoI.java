package com.nttdata.nttdatacenters.hibernate.persistence;

import java.util.List;

/**
 * Interfaz del DAO de Contract
 * 
 * @author manoli
 *
 */
public interface ContractDaoI extends CommonDaoI<Contract>{

	/**
	 * Búsqueda por indentificador de ciente.
	 * 
	 * @param customerId
	 * @return List<Contract>
	 */
	public List<Contract> searchByCustomerId(Long customer);
	
	/**
	 * Método que busca un cliente y el precio mensual del contrato por su primer apellido y el precio del contrato mayor a 100.
	 * 
	 * @param firstSurname
	 * @param monthlyPrice
	 * @return List<Contract>
	 */
	public List<Contract> searchBySurnameAndMonthlyPrice (final String firstSurname, double monthlyPrice);
}
