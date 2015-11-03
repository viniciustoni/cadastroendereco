package br.com.cadastroendereco.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Mapeamento para a tabela de Endereços da Pessoa.
 * 
 * @author Vinicius A Gai
 *
 */
@Entity
@Table(name = "PESSOA_ENDERECO")
public class PessoaEndereco implements Serializable {

	private static final long serialVersionUID = -4762674964802162853L;

	@Id
	@GeneratedValue
	@Column(name = "COD_PESSOA_ENDERECO")
	private Long codPessoaEndereco;

	@Column(name = "COD_PESSOA", nullable = false)
	private Long codPessoa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "COD_PESSOA", name = "COD_PESSOA", updatable = false, insertable = false)
	private Pessoa pessoa;

	@Column(name = "NUM_CEP", length = 8, nullable = false)
	private String numCep;

	@Column(name = "NOM_LOGRADOURO", nullable = false)
	private String nomLogradouro;

	@Column(name = "NUM_LOGRADOURO", nullable = false)
	private String numLogradouro;

	@Column(name = "DSC_COMPLEMENTO")
	private String dscComplemento;

	@Column(name = "NOM_CIDADE", nullable = false)
	private String nomCidade;

	@Column(name = "SGL_UF", nullable = false)
	private String sglUf;

	@Column(name = "NOM_BAIRRO")
	private String nomBairro;

	public Long getCodPessoaEndereco() {
		return codPessoaEndereco;
	}

	public void setCodPessoaEndereco(Long codPessoaEndereco) {
		this.codPessoaEndereco = codPessoaEndereco;
	}

	public Long getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(Long codPessoa) {
		this.codPessoa = codPessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPessoaEndereco == null) ? 0 : codPessoaEndereco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaEndereco other = (PessoaEndereco) obj;
		if (codPessoaEndereco == null) {
			if (other.codPessoaEndereco != null)
				return false;
		} else if (!codPessoaEndereco.equals(other.codPessoaEndereco))
			return false;
		return true;
	}

}
