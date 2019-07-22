package br.com.gitapi.managebean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.gitapi.DTO.Item;
import br.com.gitapi.DTO.Repositorio;
import br.com.gitapi.bo.RepositorioBO;
import br.com.gitapi.chamada.api.ApiRestGit;
import br.com.gitapi.excecao.CommitException;
import br.com.gitapi.model.RepositorioModel;

@ManagedBean
@SessionScoped
public class RepositorioBean {


	private ApiRestGit api = new ApiRestGit();

	private List<RepositorioModel> repositorioModelList;
	private RepositorioModel repositorioModel;
	private List<Item> listaRepositorioDTO;
	private String nomeRepositorio;
	private String exibirRepositorioPorNome;
	private Repositorio repositorioDTO;
	
	
	/**
	 * @author lucasrodriguesdonascimento
	 * Inicializando e listando as informacoes na pagina
	 */
	@PostConstruct
	public void init() {
		RepositorioBO ex = new RepositorioBO();
		this.repositorioModelList = ex.listarTodos();
	}
	
	/**
	 * @author lucasrodriguesdonascimento
	 * Buscando na api o repositorio solicitado
	 */
	
	public void buscarPorNome() { 
		if(nomeRepositorio.equals(null) || nomeRepositorio.equals("")) {
			this.nomeRepositorio = "/";
		}
		this.listaRepositorioDTO = api.listarRepositorioSolicitado(nomeRepositorio).iterator().next().getItems();
	}
	
	/**
	 * @author lucasrodriguesdonascimento
	 * Realiza a acao de mostras as informacoes detalhadamente do repositorio
	 * @return
	 */
	
	public String exibirRepositorioPorNome() {
		String nome = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nome"); 
		this.listaRepositorioDTO = api.listarRepositorioSolicitado(nome).iterator().next().getItems();
		return "detalhe";
	}
	
	/**
	 * Classe responsavel por trafegar os dados do TO para a classe de persistencia Model
	 * @author lucasrodriguesdonascimento
	 * @return
	 */

	public String armazenar() {

		RepositorioBO ex = new RepositorioBO();

		repositorioModel = new RepositorioModel();

		this.repositorioModel.setNomeRepositorio(listaRepositorioDTO.iterator().next().getFullName());
		this.repositorioModel.setLinkRepositorio(listaRepositorioDTO.iterator().next().getHtmlUrl());
		this.repositorioModel.setDescricao(listaRepositorioDTO.iterator().next().getDescription());
		this.repositorioModel.setLinguagem(listaRepositorioDTO.iterator().next().getLanguage());

		ex.armazenar(repositorioModel);

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "inicio?faces-redirect=true";
	}
	
	/***
	 * @author lucasrodriguesdonascimento
	 * Listando objeto por ID
	 */

	public List<RepositorioModel> listandoAll(){
		RepositorioBO ex = new RepositorioBO();
		return ex.listarTodos();
	}
	
	
	/***
	 * @author lucasrodriguesdonascimento
	 * Removendo Objeto por ID
	 */
	public void removerRepositorio() {
		
		FacesMessage msg;
		
		RepositorioBO ex = new RepositorioBO();
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idParam"); 
		try {
			ex.removerPorId(Integer.parseInt(id));
			msg = new FacesMessage("Removido!");
		} catch (NumberFormatException | CommitException e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro .....");
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

	}
	
	public void redirectParaGitHub() {
		String site = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("link");
		 try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(site);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// GET and SET

	public List<Item> getListaRepositorioDTO() {
		return listaRepositorioDTO;
	}

	public void setListaRepositorioDTO(List<Item> listaRepositorioDTO) {
		this.listaRepositorioDTO = listaRepositorioDTO;
	}

	public Repositorio getRepositorioDTO() {
		return repositorioDTO;
	}

	public void setRepositorioDTO(Repositorio repositorioDTO) {
		this.repositorioDTO = repositorioDTO;
	}

	public String getNomeRepositorio() {
		return nomeRepositorio;
	}

	public void setNomeRepositorio(String nomeRepositorio) {
		this.nomeRepositorio = nomeRepositorio;
	}

	public ApiRestGit getApi() {
		return api;
	}
	public void setApi(ApiRestGit api) {
		this.api = api;
	}
	public String getExibirRepositorioPorNome() {
		return exibirRepositorioPorNome;
	}

	public void setExibirRepositorioPorNome(String exibirRepositorioPorNome) {
		this.exibirRepositorioPorNome = exibirRepositorioPorNome;
	}

	public RepositorioModel getRepositorioModel() {
		return repositorioModel;
	}

	public void setRepositorioModel(RepositorioModel repositorioModel) {
		this.repositorioModel = repositorioModel;
	}
	public List<RepositorioModel> getRepositorioModelList() {
		return repositorioModelList;
	}
	public void setRepositorioModelList(List<RepositorioModel> repositorioModelList) {
		this.repositorioModelList = repositorioModelList;
	}
}
