package br.com.cadastroendereco.integration.to;

import java.io.Serializable;

/**
 * Classe de mapemento para o retorno do serviço de busca de CEP.
 * 
 * @author Vinicius A Gai
 *
 */
public class CepTO implements Serializable {

	private static final long serialVersionUID = -4601998600785863259L;

	private String numCep;
	private String nomLogradouro;
	private String nomCidade;
	private String sglUf;
	private String nomBairro;

	public String getNumCep() {
		return numCep;
	}

	public void setNumCep(String numCep) {
		this.numCep = numCep;
	}

	public String getNomLogradouro() {
		return nomLogradouro;
	}

	public void setNomLogradouro(String nomLogradouro) {
		this.nomLogradouro = nomLogradouro;
	}

	public String getNomCidade() {
		return nomCidade;
	}

	public void setNomCidade(String nomCidade) {
		this.nomCidade = nomCidade;
	}

	public String getSglUf() {
		return sglUf;
	}

	public void setSglUf(String sglUf) {
		this.sglUf = sglUf;
	}

	public String getNomBairro() {
		return nomBairro;
	}

	public void setNomBairro(String nomBairro) {
		this.nomBairro = nomBairro;
	}

}
