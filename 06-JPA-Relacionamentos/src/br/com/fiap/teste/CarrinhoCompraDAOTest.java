package br.com.fiap.teste;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.fiap.dao.CarrinhoCompraDAO;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.impl.CarrinhoCompraDAOImpl;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.entity.CarrinhoCompras;
import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.ItemCarrinho;
import br.com.fiap.exception.CodigoInvalidoException;
import br.com.fiap.exception.CommitException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

class CarrinhoCompraDAOTest {

	private static ClienteDAO clienteDao;
	private static CarrinhoCompraDAO carrinhoDao;
	
	@BeforeAll //método que é executado antes de todos os testes
	public static void inicializar() {
		EntityManager em = EntityManagerFactorySingleton
						.getInstance().createEntityManager();
		
		clienteDao = new ClienteDAOImpl(em);
		carrinhoDao = new CarrinhoCompraDAOImpl(em);
	}
	
	@Test
	void test() {
		//Cadastrar o carrinho e o cliente relacionados
		Cliente cliente = new Cliente("Sergio","123131155");
		CarrinhoCompras carrinho = new CarrinhoCompras(cliente, 100);
		
		ItemCarrinho item1 = new ItemCarrinho();
		item1.setValor(100);
		
		ItemCarrinho item2 = new ItemCarrinho();
		item2.setValor(500);
		
		carrinho.addItem(item1);
		carrinho.addItem(item2);
		
		try {
			//clienteDao.cadastrar(cliente);
			carrinhoDao.cadastrar(carrinho);
			carrinhoDao.commit();
		}catch(CommitException e) {
			e.printStackTrace();
			fail("Falha no teste");
		}
		
		assertNotEquals(0, cliente.getCodigo());
		assertNotEquals(0, carrinho.getCodigo());
		
		try {
			//Pesquisar o carrinho
			CarrinhoCompras busca = 
					carrinhoDao.buscar(carrinho.getCodigo());
			
			assertNotNull(busca);
			assertNotNull(busca.getCliente());
			
		} catch (CodigoInvalidoException e) {
			e.printStackTrace();
			fail("Falha no teste");
		}
		
	}

}







