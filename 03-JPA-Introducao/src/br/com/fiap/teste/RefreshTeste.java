package br.com.fiap.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.entity.Pastel;

public class RefreshTeste {

	public static void main(String[] args) {
		//Atualiza o objeto com os valores do Banco
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
		EntityManager em = fabrica.createEntityManager();
				
		//Pesquisar o pastel de código 1
		Pastel pastel = em.find(Pastel.class, 1);
		//Exibir o sabor
		System.out.println(pastel.getSabor());
		//Modificar o sabor no java e exibir
		pastel.setSabor("Picanha");
		System.out.println(pastel.getSabor());
		//Faz o refresh
		em.refresh(pastel);
		//Exibe novamente o sabor
		System.out.println(pastel.getSabor());
		
		em.close();
		fabrica.close();
	}
	
}







