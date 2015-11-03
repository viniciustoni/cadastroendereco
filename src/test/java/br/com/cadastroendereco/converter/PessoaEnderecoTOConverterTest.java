package br.com.cadastroendereco.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.cadastroendereco.entity.PessoaEndereco;
import br.com.cadastroendereco.mock.PessoaEnderecoMock;
import br.com.cadastroendereco.mock.PessoaEnderecoTOMock;
import br.com.cadastroendereco.to.PessoaEnderecoTO;

/**
 * Classe de Junit para a classe de {@link PessoaEnderecoTOConverter}
 * 
 * @author Vinicius A Gai
 *
 */
public class PessoaEnderecoTOConverterTest {

	/**
	 * Efetua o teste de conversão do objeto de {@link PessoaEndereco} para
	 * {@link PessoaEnderecoTO}
	 */
	@Test
	public void testConverterPessoaEnderecoToPessoaEnderecoTO() {

		// mock
		final PessoaEndereco pessoaEndereco = PessoaEnderecoMock.createMock();

		// Efetua a conversao
		final PessoaEnderecoTO pessoaEnderecoTO = PessoaEnderecoTOConverter
				.converterPessoaEnderecoToPessoaEnderecoTO(pessoaEndereco);

		// Validações
		Assert.assertEquals(PessoaEnderecoMock.COD_PESSOA_ENDERECO, pessoaEnderecoTO.getCodPessoaEndereco());
		Assert.assertEquals(PessoaEnderecoMock.NOM_LOGRADOURO, pessoaEnderecoTO.getNomLogradouro());
		Assert.assertEquals(PessoaEnderecoMock.NUM_LOGRADOURO, pessoaEnderecoTO.getNumLogradouro());
		Assert.assertEquals(PessoaEnderecoMock.DSC_COMPLEMENTO, pessoaEnderecoTO.getDscComplemento());
		Assert.assertEquals(PessoaEnderecoMock.NOM_BAIRRO, pessoaEnderecoTO.getNomBairro());
		Assert.assertEquals(PessoaEnderecoMock.NUM_CEP, pessoaEnderecoTO.getNumCep());
		Assert.assertEquals(PessoaEnderecoMock.NOM_CIDADE, pessoaEnderecoTO.getNomCidade());
		Assert.assertEquals(PessoaEnderecoMock.SGL_UF, pessoaEnderecoTO.getSglUf());
	}

	/**
	 * Efetua o teste de conversão do objeto de {@link PessoaEndereco} para
	 * {@link PessoaEnderecoTO}, onde os dados de PessoaEndereco é nulo
	 */
	@Test
	public void testConverterPessoaEnderecoToPessoaEnderecoTONull() {

		// mock
		final PessoaEndereco pessoaEndereco = null;

		// Efetua a conversao
		final PessoaEnderecoTO pessoaEnderecoTO = PessoaEnderecoTOConverter
				.converterPessoaEnderecoToPessoaEnderecoTO(pessoaEndereco);

		// Validações
		Assert.assertNull(pessoaEnderecoTO);
	}

	/**
	 * Efetua o teste de conversão do objeto de {@link PessoaEndereco} para
	 * {@link PessoaEnderecoTO}
	 */
	@Test
	public void testConverterPessoaEnderecoListToPessoaEnderecoTO() {

		// mock
		final List<PessoaEndereco> pessoaEnderecos = PessoaEnderecoMock.createMockList();

		// Efetua a conversao
		final List<PessoaEnderecoTO> pessoaEnderecosTO = PessoaEnderecoTOConverter
				.converterPessoaEnderecoListToPessoaEnderecoTO(pessoaEnderecos);

		// Validações
		final PessoaEnderecoTO pessoaEnderecoTO = pessoaEnderecosTO.get(0);

		Assert.assertEquals(PessoaEnderecoTOMock.COD_PESSOA_ENDERECO, pessoaEnderecoTO.getCodPessoaEndereco());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_LOGRADOURO, pessoaEnderecoTO.getNomLogradouro());
		Assert.assertEquals(PessoaEnderecoTOMock.NUM_LOGRADOURO, pessoaEnderecoTO.getNumLogradouro());
		Assert.assertEquals(PessoaEnderecoTOMock.DSC_COMPLEMENTO, pessoaEnderecoTO.getDscComplemento());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_BAIRRO, pessoaEnderecoTO.getNomBairro());
		Assert.assertEquals(PessoaEnderecoTOMock.NUM_CEP, pessoaEnderecoTO.getNumCep());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_CIDADE, pessoaEnderecoTO.getNomCidade());
		Assert.assertEquals(PessoaEnderecoTOMock.SGL_UF, pessoaEnderecoTO.getSglUf());
	}

	/**
	 * Efetua o teste de conversão do objeto de {@link PessoaEndereco} para
	 * {@link PessoaEnderecoTO}, onde os dados de PessoaEnderecoTO é nulo
	 */
	@Test
	public void testConverterPessoaEnderecoTOListAndPessoaTOToPessoaEnderecoNull() {

		// mock
		final List<PessoaEndereco> pessoaEnderecos = null;

		// Efetua a conversao
		final List<PessoaEnderecoTO> pessoaEnderecosTO = PessoaEnderecoTOConverter
				.converterPessoaEnderecoListToPessoaEnderecoTO(pessoaEnderecos);

		// Validações
		Assert.assertNull(pessoaEnderecosTO);
	}

}
