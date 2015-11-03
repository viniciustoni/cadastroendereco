package br.com.cadastroendereco.converter;

import br.com.cadastroendereco.entity.Pessoa;
import br.com.cadastroendereco.to.PessoaTO;

/**
 * Classe de converter para a entidade de {@link Pessoa}
 * 
 * @author Vinicius A Gai
 *
 */
public abstract class PessoaConverter {

	/**
	 * Converter do objeto de {@link PessoaTO} para o objeto de {@link Pessoa}.
	 * 
	 * @param pessoaTO
	 *            Dados de {@link PessoaTO}
	 * @return Dados de Pessoa após a conversão, null em caso de PessoaTO nulo.
	 */
	public static Pessoa converterPessoaTOToPessoa(final PessoaTO pessoaTO) {

		Pessoa pessoa = null;

		if (pessoaTO != null) {

			pessoa = new Pessoa();

			pessoa.setCodPessoa(pessoaTO.getCodPessoa());
			pessoa.setNomPessoa(pessoaTO.getNomPessoa());
		}

		return pessoa;
	}

}
