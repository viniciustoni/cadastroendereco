package br.com.cadastroendereco.converter;

import br.com.cadastroendereco.entity.Pessoa;
import br.com.cadastroendereco.to.PessoaTO;

/**
 * Classe contendo os métodos de converão para o objeto de {@link PessoaTO}
 * 
 * @author Vinicius A Gai
 *
 */
public abstract class PessoaTOConverter {

	/**
	 * Converter do objeto de {@link Pessoa} para o objeto de {@link PessoaTO}.
	 * 
	 * @param pessoa
	 *            Dados de {@link Pessoa}
	 * @return Dados de Pessoa após a conversão, null em caso de Pessoa nulo.
	 */
	public static PessoaTO converterPessoaToPessoaTO(final Pessoa pessoa) {

		PessoaTO pessoaTO = null;

		if (pessoa != null) {

			pessoaTO = new PessoaTO();

			pessoaTO.setCodPessoa(pessoa.getCodPessoa());
			pessoaTO.setNomPessoa(pessoa.getNomPessoa());
		}

		return pessoaTO;
	}

}
