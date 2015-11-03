package br.com.cadastroendereco.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.cadastroendereco.entity.PessoaEndereco;
import br.com.cadastroendereco.mock.PessoaEnderecoTOMock;
import br.com.cadastroendereco.mock.PessoaTOMock;
import br.com.cadastroendereco.to.PessoaEnderecoTO;

/**
 * Classe de Junit para a classe de {@link PessoaEnderecoConverter}
 * 
 * @author Vinicius A Gai
 *
 */
public class PessoaEnderecoConverterTest {

	/**
	 * Efetua o teste de conversão do objeto de {@link PessoaEnderecoTO} para
	 * {@link PessoaEndereco}
	 */
	@Test
	public void testConverterPessoaEnderecoTOAndPessoaTOToPessoaEndereco() {

		// mock
		final PessoaEnderecoTO pessoaEnderecoTO = PessoaEnderecoTOMock.createMock();

		// Efetua a conversao
		final PessoaEndereco pessoaEndereco = PessoaEnderecoConverter
				.converterPessoaEnderecoTOAndPessoaTOToPessoaEndereco(pessoaEnderecoTO, PessoaTOMock.COD_PESSOA);

		// Validações
		Assert.assertEquals(PessoaTOMock.COD_PESSOA, pessoaEndereco.getCodPessoa());
		Assert.assertEquals(PessoaEnderecoTOMock.COD_PESSOA_ENDERECO, pessoaEndereco.getCodPessoaEndereco());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_LOGRADOURO, pessoaEndereco.getNomLogradouro());
		Assert.assertEquals(PessoaEnderecoTOMock.NUM_LOGRADOURO, pessoaEndereco.getNumLogradouro());
		Assert.assertEquals(PessoaEnderecoTOMock.DSC_COMPLEMENTO, pessoaEndereco.getDscComplemento());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_BAIRRO, pessoaEndereco.getNomBairro());
		Assert.assertEquals(PessoaEnderecoTOMock.NUM_CEP, pessoaEndereco.getNumCep());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_CIDADE, pessoaEndereco.getNomCidade());
		Assert.assertEquals(PessoaEnderecoTOMock.SGL_UF, pessoaEndereco.getSglUf());
	}

	/**
	 * Efetua o teste de conversão do objeto de {@link PessoaEnderecoTO} para
	 * {@link PessoaEndereco}, onde os dados de PessoaEnderecoTO é nulo
	 */
	@Test
	public void testConverterPessoaEnderecoTOAndPessoaTOToPessoaEnderecoNull() {

		// mock
		final PessoaEnderecoTO pessoaEnderecoTO = null;

		// Efetua a conversao
		final PessoaEndereco pessoaEndereco = PessoaEnderecoConverter
				.converterPessoaEnderecoTOAndPessoaTOToPessoaEndereco(pessoaEnderecoTO, PessoaTOMock.COD_PESSOA);

		// Validações
		Assert.assertNull(pessoaEndereco);
	}

	/**
	 * Efetua o teste de conversão do objeto de {@link PessoaEnderecoTO} para
	 * {@link PessoaEndereco}
	 */
	@Test
	public void testConverterPessoaEnderecoTOListAndPessoaTOToPessoaEndereco() {

		// mock
		final List<PessoaEnderecoTO> pessoaEnderecosTO = PessoaEnderecoTOMock.createMockList();

		// Efetua a conversao
		final List<PessoaEndereco> pessoaEnderecos = PessoaEnderecoConverter
				.converterPessoaEnderecoTOListAndPessoaTOToPessoaEndereco(pessoaEnderecosTO, PessoaTOMock.COD_PESSOA);

		// Validações
		final PessoaEndereco pessoaEndereco = pessoaEnderecos.get(0);
		
		Assert.assertEquals(PessoaTOMock.COD_PESSOA, pessoaEndereco.getCodPessoa());
		Assert.assertEquals(PessoaEnderecoTOMock.COD_PESSOA_ENDERECO, pessoaEndereco.getCodPessoaEndereco());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_LOGRADOURO, pessoaEndereco.getNomLogradouro());
		Assert.assertEquals(PessoaEnderecoTOMock.NUM_LOGRADOURO, pessoaEndereco.getNumLogradouro());
		Assert.assertEquals(PessoaEnderecoTOMock.DSC_COMPLEMENTO, pessoaEndereco.getDscComplemento());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_BAIRRO, pessoaEndereco.getNomBairro());
		Assert.assertEquals(PessoaEnderecoTOMock.NUM_CEP, pessoaEndereco.getNumCep());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_CIDADE, pessoaEndereco.getNomCidade());
		Assert.assertEquals(PessoaEnderecoTOMock.SGL_UF, pessoaEndereco.getSglUf());
	}

	/**
	 * Efetua o teste de conversão do objeto de {@link PessoaEnderecoTO} para
	 * {@link PessoaEndereco}, onde os dados de PessoaEnderecoTO é nulo
	 */
	@Test
	public void testConverterPessoaEnderecoTOListAndPessoaTOToPessoaEnderecoNull() {

		// mock
		final List<PessoaEnderecoTO> pessoaEnderecosTO = null;

		// Efetua a conversao
		final List<PessoaEndereco> pessoaEnderecos = PessoaEnderecoConverter
				.converterPessoaEnderecoTOListAndPessoaTOToPessoaEndereco(pessoaEnderecosTO, PessoaTOMock.COD_PESSOA);

		// Validações
		Assert.assertNull(pessoaEnderecos);
	}

}
