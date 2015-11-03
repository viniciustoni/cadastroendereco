package br.com.cadastroendereco.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mapeamento da tabela de pessoa.
 * 
 * @author Vinicius A Gai
 *
 */
@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -9066381882853139403L;

	@Id
	@GeneratedValue
	@Column(name = "COD_PESSOA")
	private Long codPessoa;

	@Column(name = "NOM_PESSOA", length = 100)
	private String nomPessoa;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPessoa == null) ? 0 : codPessoa.hashCode());
		result = prime * result + ((nomPessoa == null) ? 0 : nomPessoa.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (codPessoa == null) {
			if (other.codPessoa != null)
				return false;
		} else if (!codPessoa.equals(other.codPessoa))
			return false;
		if (nomPessoa == null) {
			if (other.nomPessoa != null)
				return false;
		} else if (!nomPessoa.equals(other.nomPessoa))
			return false;
		return true;
	}

}
