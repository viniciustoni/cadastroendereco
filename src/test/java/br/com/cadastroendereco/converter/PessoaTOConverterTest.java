package br.com.cadastroendereco.converter;

import org.junit.Assert;
import org.junit.Test;

import br.com.cadastroendereco.entity.Pessoa;
import br.com.cadastroendereco.mock.PessoaMock;
import br.com.cadastroendereco.to.PessoaTO;

/**
 * Classe de Junit para a classe {@link PessoaTOConverter}
 * 
 * @author Vinicius A Gai
 *
 */
public class PessoaTOConverterTest {

	/**
	 * Converter os dados de {@link PessoaTO} para {@link Pessoa}
	 */
	@Test
	public void testConverterPessoaToPessoaTO() {

		// Mocks
		final Pessoa pessoa = PessoaMock.createMock();

		// Execuçao
		final PessoaTO pessoaTO = PessoaTOConverter.converterPessoaToPessoaTO(pessoa);

		// Validações
		Assert.assertEquals(PessoaMock.COD_PESSOA, pessoaTO.getCodPessoa());
		Assert.assertEquals(PessoaMock.NOM_PESSOA, pessoaTO.getNomPessoa());

	}

	/**
	 * Converter os dados de {@link Pessoa} para {@link PessoaTO}, onde o
	 * retorno é null, pois o objeto de {@link Pessoa} é nulo.
	 */
	@Test
	public void testConverterPessoaToPessoaTONull() {

		// Mocks
		final Pessoa pessoa = null;

		// Execuçao
		final PessoaTO pessoaTO = PessoaTOConverter.converterPessoaToPessoaTO(pessoa);

		// Validações
		Assert.assertNull(pessoaTO);

	}

}
