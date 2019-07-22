package br.com.gitapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author lucasrodriguesdonascimento
 * representação de entidades que necessitam de integração com o banco de dados
 */

@Entity
@Table(name = "Repositorio")
@SequenceGenerator(name = "rep" , sequenceName = "SQ_T_REPOSITORIO" , allocationSize = 1)
public class RepositorioModel implements Serializable{

	private static final long serialVersionUID = 6496397613621788872L;

	@Id
	@GeneratedValue(generator = "rep" , strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name= "nm_repositorio")
	private String nomeRepositorio;
	@Column(name= "ds_link")

	private String linkRepositorio;

	@Column(name= "ds_repositorio")
	private String descricao;

	@Column(name = "tp_linguagem")
	private String linguagem;

	public RepositorioModel() {

	}

	public RepositorioModel(Integer id, String nomeRepositorio, String linkRepositorio, String descricao,
			String linguagem) {
		super();
		this.id = id;
		this.nomeRepositorio = nomeRepositorio;
		this.linkRepositorio = linkRepositorio;
		this.descricao = descricao;
		this.linguagem = linguagem;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeRepositorio() {
		return nomeRepositorio;
	}
	public void setNomeRepositorio(String nomeRepositorio) {
		this.nomeRepositorio = nomeRepositorio;
	}
	public String getLinkRepositorio() {
		return linkRepositorio;
	}
	public void setLinkRepositorio(String linkRepositorio) {
		this.linkRepositorio = linkRepositorio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getLinguagem() {
		return linguagem;
	}
	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

}
