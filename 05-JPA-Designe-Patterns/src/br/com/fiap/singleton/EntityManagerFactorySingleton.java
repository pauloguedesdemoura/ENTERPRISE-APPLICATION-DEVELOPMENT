package br.com.fiap.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	
	
	//1- atributo est�tico que sera unico
	private static EntityManagerFactory factory;
	
	
	//2- construtror vazio e privado	
	private EntityManagerFactorySingleton() {}

	
	//3- M�todo est�tico que retorna a unica instancia
	public static EntityManagerFactory getInstance() {
		if(factory == null) {
			
			factory = Persistence
					.createEntityManagerFactory("CLIENTE_ORACLE");
			}
		return factory;
	} 
	
}
