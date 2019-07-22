package br.com.gitapi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gitapi.dao.RepositorioDAO;
import br.com.gitapi.model.RepositorioModel;

public class RepositorioImpl extends GenericDAOImpl<RepositorioModel, Integer> implements RepositorioDAO{

	public RepositorioImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<RepositorioModel> listandoAll() {
		TypedQuery<RepositorioModel> query = em.createQuery("from RepositorioModel" , RepositorioModel.class);
		// Executar a query
		return query.getResultList();
	}
	
	

}
