package br.com.cadastroendereco.converter;

import org.junit.Assert;
import org.junit.Test;

import br.com.cadastroendereco.entity.Pessoa;
import br.com.cadastroendereco.mock.PessoaTOMock;
import br.com.cadastroendereco.to.PessoaTO;

/**
 * Classe de Junit para a classe {@link PessoaConverter}
 * 
 * @author Vinicius A Gai
 *
 */
public class PessoaConverterTest {

	/**
	 * Converter os dados de {@link PessoaTO} para {@link Pessoa}
	 */
	@Test
	public void testConverterPessoaTOToPessoa() {

		// Mocks
		final PessoaTO pessoaTO = PessoaTOMock.createMock();

		// Execuçao
		final Pessoa pessoa = PessoaConverter.converterPessoaTOToPessoa(pessoaTO);

		// Validações
		Assert.assertEquals(PessoaTOMock.COD_PESSOA, pessoa.getCodPessoa());
		Assert.assertEquals(PessoaTOMock.NOM_PESSOA, pessoa.getNomPessoa());

	}

	/**
	 * Converter os dados de {@link PessoaTO} para {@link Pessoa}, onde o
	 * retorno é null, pois o objeto de PessoaTO é nulo.
	 */
	@Test
	public void testConverterPessoaTOToPessoaNull() {

		// Mocks
		final PessoaTO pessoaTO = null;

		// Execuçao
		final Pessoa pessoa = PessoaConverter.converterPessoaTOToPessoa(pessoaTO);

		// Validações
		Assert.assertNull(pessoa);

	}

}
