package br.com.gitapi.dao.impl;

import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;

import br.com.gitapi.dao.GenericDAO;
import br.com.gitapi.excecao.CommitException;
import br.com.gitapi.excecao.RegistroNaoEncontrado;

/***
 * 
 * @author lucasrodriguesdonascimento
 *
 * @param <Tabela>
 * @param <Chave>
 * 
 * DAO criada para ser capaz de abstrair um tipo qualquer de entidade da aplicação com funcionalidades espeficas SGBD'
 * Esta classe será responsável por centralizar as logicas de persistência
 *
 */

public class GenericDAOImpl <Tabela , Chave> implements GenericDAO<Tabela, Chave> {

	protected EntityManager em;

	//Pegar a tabela em tempo de excecução
	private Class<Tabela> clazz;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl(EntityManager em) {
		super();
		this.em = em;
		
		/***
		 * @author lucasrodriguesdonascimento
		 * Para obter qual o tipo de dado genérico passado como parâmetro:
		 */
		//Recuperando a Classe    
		clazz = (Class<Tabela>) ((ParameterizedType) 

				//Devolve os parametros da classe
				getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void gravar(Tabela tabela) {
		em.persist(tabela);
	}

	@Override
	public void excluir(Chave codigo) throws RegistroNaoEncontrado {
		Tabela tabela = buscar(codigo);
		if(tabela == null){
			try {
				throw new Exception("Registro não encontrado");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		em.remove(tabela);
	}

	@Override
	public Tabela buscar(Chave codigo) {
		return em.find(clazz, codigo);
	}

	@Override
	public void commit() throws CommitException {

		try{
			em.getTransaction().begin();
			em.getTransaction().commit();
		}catch(Exception e){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			e.printStackTrace();

			throw new CommitException("Erro ao realizar o commit");
		}
	}
}
