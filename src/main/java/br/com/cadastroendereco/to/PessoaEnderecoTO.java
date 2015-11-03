package br.com.cadastroendereco.to;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Classe para receber os dados de Endereço da pessoa.
 * 
 * @author Vinicius A Gai
 *
 */
@ApiModel(description = "Dados do Endereço.")
@JsonInclude(Include.NON_NULL)
public class PessoaEnderecoTO implements Serializable {

	private static final long serialVersionUID = -2276573543152196364L;

	@ApiModelProperty(value = "Código do endereço.")
	private Long codPessoaEndereco;

	@ApiModelProperty(value = "Número do CEP.")
	@NotEmpty
	private String numCep;

	@ApiModelProperty(value = "Logradouro.")
	@NotEmpty
	private String nomLogradouro;

	@ApiModelProperty(value = "Número do logradouro.")
	@NotEmpty
	private String numLogradouro;

	@ApiModelProperty(value = "Complemento do logradouro.")
	private String dscComplemento;

	@ApiModelProperty(value = "Nome da cidade.")
	@NotEmpty
	private String nomCidade;

	@ApiModelProperty(value = "UF do endereço.")
	@NotEmpty
	private String sglUf;

	@ApiModelProperty(value = "Nome do bairro.")
	private String nomBairro;

	public Long getCodPessoaEndereco() {
		return codPessoaEndereco;
	}

	public void setCodPessoaEndereco(Long codPessoaEndereco) {
		this.codPessoaEndereco = codPessoaEndereco;
	}

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

	public String getNumLogradouro() {
		return numLogradouro;
	}

	public void setNumLogradouro(String numLogradouro) {
		this.numLogradouro = numLogradouro;
	}

	public String getDscComplemento() {
		return dscComplemento;
	}

	public void setDscComplemento(String dscComplemento) {
		this.dscComplemento = dscComplemento;
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
