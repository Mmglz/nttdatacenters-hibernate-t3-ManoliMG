package com.nttdata.nttdatacenters.hibernate.main;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nttdata.nttdatacenters.hibernate.persistence.Contract;
import com.nttdata.nttdatacenters.hibernate.persistence.Customer;
import com.nttdata.nttdatacenters.hibernate.services.ContractManagementServiceI;
import com.nttdata.nttdatacenters.hibernate.services.ContractManagementServiceImpl;
import com.nttdata.nttdatacenters.hibernate.services.CustomerManagementServiceI;
import com.nttdata.nttdatacenters.hibernate.services.CustomerManagementServiceImpl;

/**
 * Clase principal
 * 
 * @author manoli
 *
 */
public class Main {
	
	/** LOGGER **/
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	/**
	 * Método principal.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		LOG.info("TRAZA DE INICIO");
		
		// Apertura de sesión.
		LOG.info("Inicio de sesión");
		final Session session = HibernateUtil.getSessionFactory().openSession();

		// Inicialización de servicios.
		final CustomerManagementServiceI customerService = new CustomerManagementServiceImpl(session);
		final ContractManagementServiceI contractService = new ContractManagementServiceImpl(session);

		// Auditoría.
		final String updateUser = "NTT_CUSTOMER_CONTRACT";
		final Date updateDate = new Date();

		LOG.info("Inicio generación de clientes y contratos");

		// Generación de clientes.
		final Customer customer1 = new Customer();
		customer1.setName("Lara");
		customer1.setFirstSurname("Perez");
		customer1.setSecondSurname("Garcia");
		customer1.setDni("41567469H");
		customer1.setCity("Sevilla");
		customer1.setUpdatedDate(updateDate);
		customer1.setUpdatedUser(updateUser);

		final Customer customer2 = new Customer();
		customer2.setName("Pablo");
		customer2.setFirstSurname("Camacho");
		customer2.setSecondSurname("Delgado");
		customer2.setDni("85317614L");
		customer2.setCity("Huelva");
		customer2.setUpdatedDate(updateDate);
		customer2.setUpdatedUser(updateUser);

		final Customer customer3 = new Customer();
		customer3.setName("Laura");
		customer3.setFirstSurname("Perez");
		customer3.setSecondSurname("Rodriguez");
		customer3.setDni("56841394S");
		customer3.setCity("Sevilla");
		customer3.setUpdatedDate(updateDate);
		customer3.setUpdatedUser(updateUser);

		final Customer customer4 = new Customer();
		customer4.setName("Pepe");
		customer4.setFirstSurname("Perez");
		customer4.setSecondSurname("Roldan");
		customer4.setDni("20553274R");
		customer4.setCity("Cadiz");
		customer4.setUpdatedDate(updateDate);
		customer4.setUpdatedUser(updateUser);

		final Customer customer5 = new Customer();
		customer5.setName("Estrella");
		customer5.setFirstSurname("Lopez");
		customer5.setSecondSurname("Blanco");
		customer5.setDni("17349835F");
		customer5.setCity("Sevilla");
		customer5.setUpdatedDate(updateDate);
		customer5.setUpdatedUser(updateUser);

		// Generación de contratos.
		final Contract contract1 = new Contract();
		contract1.setValidityDate("28-02-20215");
		contract1.setMonthlyPrice(95.3);
		contract1.setCustomer(customer1);
		contract1.setUpdatedDate(updateDate);
		contract1.setUpdatedUser(updateUser);

		final Contract contract2 = new Contract();
		contract2.setValidityDate("01-12-2020");
		contract2.setExpirationDate("12-05-2022");
		contract2.setMonthlyPrice(85.9);
		contract2.setCustomer(customer2);
		contract2.setUpdatedDate(updateDate);
		contract2.setUpdatedUser(updateUser);

		final Contract contract3 = new Contract();
		contract3.setValidityDate("20-08-2012");
		contract3.setExpirationDate("31-01-2016");
		contract3.setMonthlyPrice(100);
		contract3.setCustomer(customer3);
		contract3.setUpdatedDate(updateDate);
		contract3.setUpdatedUser(updateUser);

		final Contract contract4 = new Contract();
		contract4.setValidityDate("30-04-2018");
		contract4.setExpirationDate("23-04-2019");
		contract4.setMonthlyPrice(26.7);
		contract4.setCustomer(customer4);
		contract4.setUpdatedDate(updateDate);
		contract4.setUpdatedUser(updateUser);

		final Contract contract5 = new Contract();
		contract5.setValidityDate("06-10-2011");
		contract5.setExpirationDate("19-07-2017");
		contract5.setMonthlyPrice(120);
		contract5.setCustomer(customer5);
		contract5.setUpdatedDate(updateDate);
		contract5.setUpdatedUser(updateUser);

		LOG.info("Fin generación de clientes y contratos");

		LOG.info("Inicio inserciones de clientes y contratos");

		// Inserciones de los clientes.
		customerService.insertNewCustomer(customer1);
		customerService.insertNewCustomer(customer2);
		customerService.insertNewCustomer(customer3);
		customerService.insertNewCustomer(customer4);
		customerService.insertNewCustomer(customer5);

		// Inserciones de contratos.
		contractService.insertNewContract(contract1);
		contractService.insertNewContract(contract2);
		contractService.insertNewContract(contract3);
		contractService.insertNewContract(contract4);
		contractService.insertNewContract(contract5);

		LOG.info("Fin inserciones de clientes y contratos");
		

		LOG.info("Inicio de consultas y modificaciones de los clientes");

		// Consulta de todos los clientes.
		System.out.println("---Obtención de todos los clientes---");
		
		List<Customer> customersList = customerService.searchAll();
		for (final Customer customers : customersList) {
			
			System.out.println(customers.toString() + "\n");

		}

		// Actualización del nombre del cliente 4.
		customer4.setName("José");
		customerService.updateCustomer(customer4);

		// Consulta por ID.
		System.out.println("\n---Obtención de cliente por ID---");
		
		Customer searchId = customerService.searchById(4L);
		
		if(searchId != null) {
			
			System.out.println(searchId.toString());
			
		} else {
			
			LOG.error("Cliente no encontrado");
		}

		// Eliminación del contrato 5.
		contractService.deleteContract(contract5);

		// Consulta de un cliente por nombre y apellidos.
		System.out.println("\n---Obtención de cliente por nombre y apellidos---");
		
		List<Customer> searchNameAndSurnames = customerService.searchByNameAndSurnames("Pablo", "Camacho", "Delgado");
		
		for (final Customer customer : searchNameAndSurnames) {
			System.out.println(customer.toString() + "\n");
		}

		// Consulta por primer apellido de cliente y por el precio mensual del contrato.
		System.out.println("\n---Obtención por primer apellido y precio mensual del contrato---");
		
		List<Contract> searchMonthlyPrice = contractService.searchBySurnameAndMonthlyPrice("Perez", 100);
		for (final Contract contract : searchMonthlyPrice) {
			System.out.println(contract.getCustomer().getDni() + "  " + contract.getCustomer().getName() + "  "
					+ contract.getMonthlyPrice());
		}

		// Consulta de cliente por nombre y ciudad.
		System.out.println("\n---Obtención de cliente por nombre y ciudad---");

		List<Customer> searchNameAndCity = customerService.searchByNameAndCity("L%", "Sevilla");
		for (final Customer customer : searchNameAndCity) {
			System.out.println(customer.toString() + "\n");
		}

		// Consulta de un contrato por el ID del cliente.
		System.out.println("\n---Obtención de contrato por el ID del cliente---");
		
		List<Contract> seacrhIdCustomer = contractService.searchByCustomerId(1L);

		for (final Contract contract : seacrhIdCustomer) {
			System.out.println(contract.toString());
		}

		LOG.info("Fin de consultas y modificaciones de los clientes");

		// Cierre de sesión.
		session.close();
		LOG.info("Cierre de sesión");
		
		LOG.info("TRAZA DE FIN");
	}
}
