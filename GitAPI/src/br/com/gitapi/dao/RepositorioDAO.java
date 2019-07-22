package br.com.gitapi.dao;

import java.util.List;

import br.com.gitapi.model.RepositorioModel;

public interface RepositorioDAO extends GenericDAO<RepositorioModel,Integer>{
	
	List<RepositorioModel> listandoAll ();

}
