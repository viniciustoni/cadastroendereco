package br.com.cadastroendereco.mock;

import br.com.cadastroendereco.entity.Pessoa;

/**
 * Classe para gerar o Mock para a entidade de Pessoa.
 * 
 * @author Vinicius A Gai
 *
 */
public final class PessoaMock {

	public static final Long COD_PESSOA = 1l;
	public static final String NOM_PESSOA = "Vinicius A Gai";

	/**
	 * Cria o objeto de Mock para a pessoa.
	 * 
	 * @return Dados da pessoa.
	 */
	public static Pessoa createMock() {
		final Pessoa pessoa = new Pessoa();

		pessoa.setCodPessoa(COD_PESSOA);
		pessoa.setNomPessoa(NOM_PESSOA);

		return pessoa;
	}

}
