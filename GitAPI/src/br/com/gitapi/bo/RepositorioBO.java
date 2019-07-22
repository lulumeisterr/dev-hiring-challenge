package br.com.gitapi.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.gitapi.dao.RepositorioDAO;
import br.com.gitapi.dao.impl.RepositorioImpl;
import br.com.gitapi.excecao.CommitException;
import br.com.gitapi.excecao.RegistroNaoEncontrado;
import br.com.gitapi.model.RepositorioModel;
import br.com.gitapi.singleton.EntityManagerFactorySingleton;

/***
 * Classe responsavel por persistir as informacoes recuperadas da api no banco de dados
 * @author lucasrodriguesdonascimento
 *
 */

public class RepositorioBO {

	EntityManagerFactory fa = EntityManagerFactorySingleton.getInstance();
	EntityManager em = fa.createEntityManager(); 
	
	
	/**
	 * @author lucasrodriguesdonascimento
	 * Listando todos repositorios cadastrados no banco
	 * @return repositorio
	 */
	
	public List<RepositorioModel> listarTodos(){
		RepositorioDAO rDAO = new RepositorioImpl(em);
		List<RepositorioModel> lista = rDAO.listandoAll();
		em.close();
		return lista;
	}

	/**
	 * @author lucasrodriguesdonascimento
	 * Salvando repositorios no banco
	 * @return repositorio
	 */
	
	public void armazenar(RepositorioModel repositorio){

		RepositorioDAO rDAO = new RepositorioImpl(em);
		try{
			rDAO.gravar(repositorio);
			rDAO.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}

	}
	
	/**
	 * @author lucasrodriguesdonascimento
	 * Removendo repositorio por ID
	 * @return repositorio
	 */
	
	public void removerPorId(Integer id) throws CommitException {
		RepositorioDAO rDAO = new RepositorioImpl(em);
		try {
			rDAO.excluir(id);
			rDAO.commit();
		} catch (RegistroNaoEncontrado e) {
			e.printStackTrace();
		}finally{
			em.close();
		}
	}

}
