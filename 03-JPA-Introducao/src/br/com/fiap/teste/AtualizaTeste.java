package br.com.fiap.teste;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.entity.Massa;
import br.com.fiap.entity.Pastel;

public class AtualizaTeste {

	public static void main(String[] args) {
		//Criar a fabrica de entity manager
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
		//Criar o entity manager
		EntityManager em = fabrica.createEntityManager();
		
		//Criar um novo objeto com um id existente no banco
		Pastel pastel = new Pastel(1, "Frango", 5, true, 
				Massa.SEM_GLUTEN, Calendar.getInstance(), null);
		
		//Atualizar o pastel na base de dados
		em.merge(pastel);
		
		em.getTransaction().begin();
		em.getTransaction().commit();
		
		//fechar as paradas
		em.close();
		fabrica.close();
	}

}