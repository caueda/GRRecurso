package br.com.grrecurso.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerFactoryCreation {

	/**
	 * Configurar o MySQL
	 * mysql -u root
	 * mysql> create database grrecurso;
	 * mysql> create user 'grrecurso'@'localhost' identified by 'welcome1';
	 * mysql> grant all on grrecurso.* to 'grrecurso'@'localhost';
	 * @param args
	 */
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("grrecurso");

		factory.close();
	}
}
