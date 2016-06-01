package br.com.grrecurso.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	/**
	 * Configurar o MySQL
	 * mysql -u root -p
	 * mysql> create database grrecurso;
	 * mysql> grant all on grrecurso.* to 'grrecurso'@'localhost' identified by 'welcome1';
	 * @param args
	 */
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("grrecurso");

		factory.close();
	}
}
