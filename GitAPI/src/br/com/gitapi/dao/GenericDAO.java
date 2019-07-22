package br.com.gitapi.dao;

import br.com.gitapi.excecao.CommitException;
import br.com.gitapi.excecao.RegistroNaoEncontrado;

/**
 * 
 * @author lucasrodriguesdonascimento
 *
 * @param <Tabela>
 * @param <Chave>
 * 
 * Interface responsavel por facilitar a utilizacao  dos metodos CRUD que pode ser
 * aplicado para qualquer classe , assim sera reaproveitada no DAO generico para que o codigo nao se tornar repetitivo
 * Necessario passar a Classe e ID da classe(PK)
 */

public interface GenericDAO <Tabela,Chave> {

	void gravar(Tabela tabela);

	void excluir(Chave codigo)throws RegistroNaoEncontrado;

	void commit() throws CommitException;

	Tabela buscar(Chave codigo);

}
