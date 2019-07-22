package br.com.gitapi.chamada.api;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.gitapi.DTO.Repositorio;


/**
 * 
 * @author lucasrodriguesdonascimento
 * Relizando uma instancia de Client para se obter uma instancia de webResource que sera utilizado para fazer a 
 * chamada na API
 *
 */

public class ApiRestGit {

	private Client client;
	private WebResource webResource;
	private ClientResponse response;
	
	/**
	 * @author lucasrodriguesdonascimento
	 * Relizando a chamada na API
	 */
	
	
	public ApiRestGit() {
		 client = Client.create();
		 webResource = client.resource("https://api.github.com/search/repositories");
	}
		
	/**
	 * 
	 * @param string
	 * @return jsongithub
	 * Passando parametro para o endpoint passado no webResource e listando todos os valores definido por um nome
	 * que seria o repositorio a ser procurado na api do github
	 */
	
	public List<Repositorio> listarRepositorioSolicitado(String param) {
		this.response = webResource.queryParam("q", String.valueOf(param)).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if(this.response.getStatus() != 200) {
			try {
				throw new Exception("Erro ao realizar a requisicao : " + response.getStatus());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Arrays.asList(webResource.queryParam("q", param).accept(MediaType.APPLICATION_JSON).get(Repositorio.class));
	}
	
}
