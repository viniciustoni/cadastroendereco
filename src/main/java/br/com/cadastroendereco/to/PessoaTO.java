package br.com.cadastroendereco.to;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Dados da Pessoa para o serviço.
 * 
 * @author Vinicius A Gai
 *
 */
@ApiModel(description = "Dados da Pessoa.")
@JsonInclude(Include.NON_NULL)
public class PessoaTO implements Serializable {

	private static final long serialVersionUID = 317550450072298952L;

	@ApiModelProperty(value = "Código da pessoa.")
	private Long codPessoa;

	@ApiModelProperty(value = "Nome da pessoa.")
	@NotEmpty
	private String nomPessoa;

	@Valid
	private List<PessoaEnderecoTO> pessoaEnderecosTO;

	public Long getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(Long codPessoa) {
		this.codPessoa = codPessoa;
	}

	public String getNomPessoa() {
		return nomPessoa;
	}

	public void setNomPessoa(String nomPessoa) {
		this.nomPessoa = nomPessoa;
	}

	public List<PessoaEnderecoTO> getPessoaEnderecosTO() {
		return pessoaEnderecosTO;
	}

	public void setPessoaEnderecosTO(List<PessoaEnderecoTO> pessoaEnderecosTO) {
		this.pessoaEnderecosTO = pessoaEnderecosTO;
	}

}
