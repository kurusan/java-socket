package com.bank.server;

import java.util.ArrayList;

import com.bank.dao.CommonDAO;
import com.bank.logic.Account;
import com.bank.logic.Agency;
import com.bank.logic.Customer;
import com.bank.logic.Operation;
import com.bank.logic.auth.User;

public class ProvidedServices{
	
	private static CommonDAO cdao = new CommonDAO();
	
	/* Retrieve user Authenticate*/
	public static ArrayList <User> authRequest(String login, String password) {
		return cdao.select(User.class, "User", null);
	}
	
	public static void createUser(User user) {
		cdao.insert(user, "User");
	}
	
	/* These four methods are called when creation is required*/
	/**
	 * Call this method when created Agency is needed
	 * @param agency
	 */
	public static void createAgency(Agency agency){
		cdao.insert(agency, "Agency");
	}
	
	/**
	 * Call this method when created Customer is needed
	 * @param customer
	 */
	public static void createCustomer(Customer customer){
		cdao.insert(customer, "Customer");
	}
	
	/**
	 * Call this method when created Account is needed
	 * @param account
	 */
	public static void createAccount(Account account){
		cdao.insert(account, "Account");
	}
	
	/**
	 * Call this method when created Operation is needed
	 * @param operation
	 */
	public static void createOperation(Operation operation){
		cdao.insert(operation, "Operation");
	}
	
	/* These methods are called when select is required*/
	public static ArrayList <Agency> getAgencies(){
		return cdao.select(Agency.class, "Agency", null);
	}
	
	public static ArrayList <Customer> getCustomers(){
		return cdao.select(Customer.class, "Customer", null);
	}
	
	public static ArrayList <Account> getAccounts(){
		return cdao.select(Account.class, "Account", null);
	}
	
	public static ArrayList <Operation> getOperations(){
		return cdao.select(Operation.class, "Operation", null);
	}

	public static ArrayList <Customer> getCustomersByAgency(String agencyName){
		return cdao.select(Customer.class, "Customer", "agName = '" + agencyName + "'");
	}

	public static ArrayList <Account> getAccountsByCustomer(String customerID){
		return cdao.select(Account.class, "Account", "cusID = '" + customerID + "'");
	}
	
	public static ArrayList <Operation> getOperationByAccount(String accountID){
		return cdao.select(Operation.class, "Operation", "accountID = '" + accountID + "'");
	}

	public static String getAccountStat(String temp){
		
		String stat ="";
		stat += "Prenom : " ;
		stat += "Nom : ";
		stat += "Nom de l'agence : ";
		stat += "Adresse de l'agence : ";
		stat += "Numéro de compte : ";
		stat += "Solde : ";
		stat += "Statut du compte : ";
		stat += "Derniére transaction : ";
		
		return stat;
	}

	/* These four methods are called when delete is required*/
	
	public static int deleteAccount(Account account, String clause) {
		return cdao.delete(account, "Account", clause);
	}
	
	public static int deleteOperation(Operation operation, String clause) {
		return cdao.delete(operation, "Operation", clause);
	}
	
	public static int deleteAgency(Agency agency, String clause) {
		return cdao.delete(agency, "Agency", clause);
	}
	
	public static int deleteCustomer(Customer customer, String clause) {
		return cdao.delete(customer, "Customer", clause);
	}
	
	/* These four methods are called when update is required*/
	
	public static int updateAccount(Account account, String clause) {
		return cdao.update(account, "Account", clause);
	}
	
	public static int updateOperation(Operation operation, String clause) {
		return cdao.update(operation, "Operation", clause);
	}
	
	public static int updateAgency(Agency agency, String clause) {
		return cdao.update(agency, "Agency", clause);
	}
	
	public static int updateCustomer(Customer customer, String clause) {
		return cdao.update(customer, "Customer", clause);
	}
	
}
