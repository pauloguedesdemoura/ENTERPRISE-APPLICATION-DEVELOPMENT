package br.com.fiap.teste;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.entity.Massa;
import br.com.fiap.entity.Pastel;

public class CadastroTeste {

	public static void main(String[] args) {
		//Criar um entity manager (gerenciador de entidades)
		//Primeiro, criar a fabrica de entity manager
		EntityManagerFactory fabrica = //nome que está no persistence.xml
			Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
		//Criar o entity manager utilizando a fábrica
		EntityManager em = fabrica.createEntityManager();
	
		//Instanciar um pastel
		Pastel pastel = new Pastel("Carne", 3, false, Massa.TRADICIONAL, 
				new GregorianCalendar(2019, Calendar.FEBRUARY, 17), null);
		
		em.persist(pastel);
		
		em.getTransaction().begin();
		em.getTransaction().commit();
		
		em.close();
		fabrica.close();
	}	
	
}




