package br.com.fiap.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.fiap.dao.MercadoDAO;
import br.com.fiap.dao.impl.MercadoDAOImpl;
import br.com.fiap.entity.CategoriaMercado;
import br.com.fiap.entity.Mercado;
import br.com.fiap.exception.CodigoInvalidoException;
import br.com.fiap.exception.CommitException;

class MercadoDAOTest {

	private static MercadoDAO dao;

	@BeforeAll // executa antes de todos os testes
	public static void init() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("teste");
		EntityManager em = fabrica.createEntityManager();
		dao = new MercadoDAOImpl(em);
	}

	private Mercado mercado;
	
	@BeforeEach // executa antes de cada teste
	public void antesDeCadaTeste() {
		mercado = new Mercado("Andorinha", "Santana", CategoriaMercado.HIPER,
				new GregorianCalendar(1999, Calendar.AUGUST, 1));

		try {
			dao.cadastrar(mercado);
			dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			fail("Falha no commit");
		}
	}
	
	@Test
	@DisplayName("Teste de remoção com sucesso")
	void remover() {
		try {
			//Remover 
			dao.remover(mercado.getCodigo());
			dao.commit();
		} catch (CodigoInvalidoException | CommitException e) {
			e.printStackTrace();
		}
		
		//Validar se removeu
		
		assertThrows(CodigoInvalidoException.class, 
			()-> dao.buscar(mercado.getCodigo()));
		
//		try {
//			Mercado busca = dao.buscar(mercado.getCodigo());
//			fail("Falha");
//		} catch (CodigoInvalidoException e) {
//			//sucesso!
//		}
		
		
	}

	@Test
	@DisplayName("Teste de alteração com sucesso")
	void alterar() {
		// alterar
		Mercado alterado = new Mercado(mercado.getCodigo(),
			"Javinha", "Av paulista", CategoriaMercado.SUPER, 
			Calendar.getInstance());
		
		try {
			dao.alterar(alterado);
			dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			fail("Falha no teste");
		}
		
		// buscar e validar se alterou
		try {
			Mercado busca = dao.buscar(mercado.getCodigo());
			assertNotEquals("Andorinha", busca.getNome());
			assertEquals("Javinha", busca.getNome());
		} catch (CodigoInvalidoException e) {
			e.printStackTrace();
			fail("Falha no teste");
		}
	}

	@Test
	@DisplayName("Teste de busca do DAO genérico")
	void buscaTest() {

		try {
			Mercado busca = dao.buscar(mercado.getCodigo());

			// Arrange - validação do resultado
			assertNotNull(busca);
			assertEquals("Andorinha", busca.getNome());

		} catch (CodigoInvalidoException e) {
			e.printStackTrace();
			fail("Falha na pesquisa");
		}

	}

	@Test
	void cadastrarTest() {
		// Assert - validar o resultado
		// O banco gerou um código para o mercado..
		assertNotEquals(0, mercado.getCodigo());
	}

}




