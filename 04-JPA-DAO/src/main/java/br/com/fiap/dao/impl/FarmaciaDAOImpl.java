package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.FarmaciaDAO;
import br.com.fiap.entity.Farmacia;
import br.com.fiap.exception.CommitException;

public class FarmaciaDAOImpl implements FarmaciaDAO {

	private EntityManager em;
	
	public FarmaciaDAOImpl(EntityManager em) {
		super();
		this.em = em;
	}

	public void cadastrar(Farmacia farmacia) {
		em.persist(farmacia);
	}

	public void deletar(int codigo) {
		Farmacia farmacia = buscar(codigo);
		em.remove(farmacia);
	}

	public void atualizar(Farmacia farmacia) {
		em.merge(farmacia);
	}

	public Farmacia buscar(int codigo) {
		return em.find(Farmacia.class, codigo);
	}

	public void commit() throws CommitException {
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new CommitException();
		}
	}

}





