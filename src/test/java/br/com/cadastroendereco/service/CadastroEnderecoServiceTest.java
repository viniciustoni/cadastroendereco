package br.com.cadastroendereco.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.cadastroendereco.entity.Pessoa;
import br.com.cadastroendereco.entity.PessoaEndereco;
import br.com.cadastroendereco.exception.NenhumaPessoaEncontradaException;
import br.com.cadastroendereco.exception.NenhumaPessoaEnderecoEncontradaException;
import br.com.cadastroendereco.integration.BuscaCepDelegate;
import br.com.cadastroendereco.integration.exception.BuscaCepException;
import br.com.cadastroendereco.integration.exception.CepInvalidoException;
import br.com.cadastroendereco.integration.exception.NenhumCepEncontradoException;
import br.com.cadastroendereco.mock.PessoaEnderecoMock;
import br.com.cadastroendereco.mock.PessoaEnderecoTOMock;
import br.com.cadastroendereco.mock.PessoaMock;
import br.com.cadastroendereco.mock.PessoaTOMock;
import br.com.cadastroendereco.repository.PessoaEnderecoRepository;
import br.com.cadastroendereco.repository.PessoaRepository;
import br.com.cadastroendereco.service.impl.CadastroEnderecoServiceImpl;
import br.com.cadastroendereco.to.PessoaEnderecoTO;
import br.com.cadastroendereco.to.PessoaTO;

/**
 * Junit para a classe de {@link CadastroEnderecoService}
 * 
 * @author Vinicius A Gai
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CadastroEnderecoServiceTest {

	@Mock
	private PessoaRepository pessoaRepository;

	@Mock
	private PessoaEnderecoRepository pessoaEnderecoRepository;

	@Mock
	private BuscaCepDelegate buscaCepDelegate;

	@InjectMocks
	private CadastroEnderecoService cadastroEnderecoService = new CadastroEnderecoServiceImpl();

	@Captor
	private ArgumentCaptor<Pessoa> pessoaCaptor;

	@Captor
	private ArgumentCaptor<PessoaEndereco> pessoaEnderecoCaptor;

	/**
	 * Executa a gravação dos dados de endereço, para uma nova pessoa
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 * @throws CepInvalidoException
	 */
	@Test
	public void testGravaEnderecoNovaPessoa()
			throws NenhumaPessoaEncontradaException, NenhumaPessoaEnderecoEncontradaException, CepInvalidoException {

		// cria o objeto de mock
		final PessoaTO pessoaTO = PessoaTOMock.createMock(null);
		final Pessoa pessoa = PessoaMock.createMock();

		// Prepara os dados
		pessoaTO.getPessoaEnderecosTO().get(0).setCodPessoaEndereco(null);

		// Eventos
		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

		// executa o método
		cadastroEnderecoService.gravaEndereco(pessoaTO);

		// verificacões.
		verify(pessoaRepository, times(1)).save(pessoaCaptor.capture());
		verify(pessoaEnderecoRepository, times(1)).save(pessoaEnderecoCaptor.capture());

		// Asserts
		Assert.assertEquals(PessoaMock.COD_PESSOA, pessoa.getCodPessoa());
		Assert.assertEquals(PessoaTOMock.NOM_PESSOA, pessoa.getNomPessoa());

		// Validações
		final PessoaEndereco pessoaEndereco = pessoaEnderecoCaptor.getValue();
		Assert.assertEquals(PessoaMock.COD_PESSOA, pessoaEndereco.getCodPessoa());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_LOGRADOURO, pessoaEndereco.getNomLogradouro());
		Assert.assertEquals(PessoaEnderecoTOMock.NUM_LOGRADOURO, pessoaEndereco.getNumLogradouro());
		Assert.assertEquals(PessoaEnderecoTOMock.DSC_COMPLEMENTO, pessoaEndereco.getDscComplemento());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_BAIRRO, pessoaEndereco.getNomBairro());
		Assert.assertEquals(PessoaEnderecoTOMock.NUM_CEP, pessoaEndereco.getNumCep());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_CIDADE, pessoaEndereco.getNomCidade());
		Assert.assertEquals(PessoaEnderecoTOMock.SGL_UF, pessoaEndereco.getSglUf());
	}

	/**
	 * Executa a gravação dos dados de endereço, para uma pessoa existente,
	 * localizada pelo ID. Com um endereço existente, ou seja o teste contempla
	 * 2 cenários: <br>
	 * <ul>
	 * <li>Alteraçao de Pessoa localizada pelo ID</li>
	 * <li>Alteraçao de Endereço localizado pelo ID</li>
	 * </ul>
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 * @throws CepInvalidoException
	 */
	@Test
	public void testGravaEnderecoPessoaExistenteId()
			throws NenhumaPessoaEncontradaException, NenhumaPessoaEnderecoEncontradaException, CepInvalidoException {

		// cria o objeto de mock
		final PessoaTO pessoaTO = PessoaTOMock.createMock();
		final Pessoa pessoa = PessoaMock.createMock();
		final PessoaEndereco pessoaEnderecoMock = PessoaEnderecoMock.createMock();

		// Eventos
		when(pessoaRepository.findOne(PessoaTOMock.COD_PESSOA)).thenReturn(pessoa);
		when(pessoaEnderecoRepository.findByCodPessoaEnderecoAndCodPessoa(PessoaEnderecoTOMock.COD_PESSOA_ENDERECO,
				PessoaMock.COD_PESSOA)).thenReturn(pessoaEnderecoMock);
		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

		// executa o método
		cadastroEnderecoService.gravaEndereco(pessoaTO);

		// verificacões.
		verify(pessoaRepository, times(1)).save(pessoaCaptor.capture());
		verify(pessoaEnderecoRepository, times(1)).save(pessoaEnderecoCaptor.capture());

		// Asserts
		Assert.assertEquals(PessoaMock.COD_PESSOA, pessoa.getCodPessoa());
		Assert.assertEquals(PessoaTOMock.NOM_PESSOA, pessoa.getNomPessoa());

		// Validações
		final PessoaEndereco pessoaEndereco = pessoaEnderecoCaptor.getValue();
		Assert.assertEquals(PessoaMock.COD_PESSOA, pessoaEndereco.getCodPessoa());
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
	 * Executa a gravação dos dados de endereço, para uma pessoa existente,
	 * localizada pelo Nome. Com um endereço novo.
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 * @throws CepInvalidoException
	 */
	@Test
	public void testGravaEnderecoPessoaExistenteNome()
			throws NenhumaPessoaEncontradaException, NenhumaPessoaEnderecoEncontradaException, CepInvalidoException {

		// cria o objeto de mock
		final PessoaTO pessoaTO = PessoaTOMock.createMock(null);
		final Pessoa pessoa = PessoaMock.createMock();

		// Prepara os dados
		pessoaTO.getPessoaEnderecosTO().get(0).setCodPessoaEndereco(null);

		// Eventos
		when(pessoaRepository.findByNomPessoaIgnoreCase(PessoaTOMock.NOM_PESSOA)).thenReturn(pessoa);
		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

		// executa o método
		cadastroEnderecoService.gravaEndereco(pessoaTO);

		// verificacões.
		verify(pessoaRepository, times(1)).save(pessoaCaptor.capture());
		verify(pessoaEnderecoRepository, times(1)).save(pessoaEnderecoCaptor.capture());

		// Asserts
		Assert.assertEquals(PessoaMock.COD_PESSOA, pessoa.getCodPessoa());
		Assert.assertEquals(PessoaTOMock.NOM_PESSOA, pessoa.getNomPessoa());

		// Validações
		final PessoaEndereco pessoaEndereco = pessoaEnderecoCaptor.getValue();
		Assert.assertEquals(PessoaMock.COD_PESSOA, pessoaEndereco.getCodPessoa());
		Assert.assertNull(pessoaEndereco.getCodPessoaEndereco());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_LOGRADOURO, pessoaEndereco.getNomLogradouro());
		Assert.assertEquals(PessoaEnderecoTOMock.NUM_LOGRADOURO, pessoaEndereco.getNumLogradouro());
		Assert.assertEquals(PessoaEnderecoTOMock.DSC_COMPLEMENTO, pessoaEndereco.getDscComplemento());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_BAIRRO, pessoaEndereco.getNomBairro());
		Assert.assertEquals(PessoaEnderecoTOMock.NUM_CEP, pessoaEndereco.getNumCep());
		Assert.assertEquals(PessoaEnderecoTOMock.NOM_CIDADE, pessoaEndereco.getNomCidade());
		Assert.assertEquals(PessoaEnderecoTOMock.SGL_UF, pessoaEndereco.getSglUf());
	}

	/**
	 * Executa a gravação dos dados de endereço, com problemas no serviço de CEP.
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 * @throws CepInvalidoException
	 * @throws BuscaCepException
	 * @throws NenhumCepEncontradoException
	 */
	@Test(expected = CepInvalidoException.class)
	public void testGravaEnderecoPessoaCepInvalido()
			throws NenhumaPessoaEncontradaException, NenhumaPessoaEnderecoEncontradaException, CepInvalidoException,
			NenhumCepEncontradoException, BuscaCepException {

		// cria o objeto de mock
		final PessoaTO pessoaTO = PessoaTOMock.createMock(null);
		final Pessoa pessoa = PessoaMock.createMock();

		// Eventos
		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
		when(buscaCepDelegate.buscaCep(anyString())).thenThrow(new CepInvalidoException());

		// executa o método
		cadastroEnderecoService.gravaEndereco(pessoaTO);

	}

	/**
	 * Executa a gravação dos dados de endereço, onde nao localiza a pessoa.
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 * @throws CepInvalidoException
	 */
	@Test(expected = NenhumaPessoaEncontradaException.class)
	public void testGravaEnderecoPessoaNaoEncontrada()
			throws NenhumaPessoaEncontradaException, NenhumaPessoaEnderecoEncontradaException, CepInvalidoException {

		// cria o objeto de mock
		final PessoaTO pessoaTO = PessoaTOMock.createMock();

		// executa o método
		cadastroEnderecoService.gravaEndereco(pessoaTO);
	}

	/**
	 * Executa a gravação dos dados de endereço, onde não localiza o endereço do cliente.
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 * @throws CepInvalidoException
	 */
	@Test(expected = NenhumaPessoaEnderecoEncontradaException.class)
	public void testGravaEnderecoPessoaEnderecoInexistente()
			throws NenhumaPessoaEncontradaException, NenhumaPessoaEnderecoEncontradaException, CepInvalidoException {

		// cria o objeto de mock
		final PessoaTO pessoaTO = PessoaTOMock.createMock();
		final Pessoa pessoa = PessoaMock.createMock();

		// Eventos
		when(pessoaRepository.findOne(PessoaTOMock.COD_PESSOA)).thenReturn(pessoa);
		when(pessoaEnderecoRepository.findByCodPessoaEnderecoAndCodPessoa(PessoaEnderecoTOMock.COD_PESSOA_ENDERECO,
				PessoaMock.COD_PESSOA)).thenReturn(null);
		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

		// executa o método
		cadastroEnderecoService.gravaEndereco(pessoaTO);
	}

	/**
	 * Junit para a remoção dos dados de pessoa
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 */
	@Test
	public void testRemovePessoa() throws NenhumaPessoaEncontradaException {

		// Mocks.
		final Pessoa pessoa = PessoaMock.createMock();
		final List<PessoaEndereco> pessoaEnderecos = PessoaEnderecoMock.createMockList();

		// Eventos
		when(pessoaRepository.findOne(PessoaMock.COD_PESSOA)).thenReturn(pessoa);
		when(pessoaEnderecoRepository.findByCodPessoa(PessoaMock.COD_PESSOA)).thenReturn(pessoaEnderecos);

		// executa o método
		cadastroEnderecoService.removePessoa(PessoaMock.COD_PESSOA);

		// Validaçoes
		verify(pessoaEnderecoRepository, times(1)).delete(pessoaEnderecos);
		verify(pessoaRepository, times(1)).delete(pessoa);
	}

	/**
	 * Junit para a remoção dos dados de pessoa
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 */
	@Test
	public void testRemovePessoaPessoaSemEnderecoCadastrado() throws NenhumaPessoaEncontradaException {

		// Mocks.
		final Pessoa pessoa = PessoaMock.createMock();
		final List<PessoaEndereco> pessoaEnderecos = null;

		// Eventos
		when(pessoaRepository.findOne(PessoaMock.COD_PESSOA)).thenReturn(pessoa);
		when(pessoaEnderecoRepository.findByCodPessoa(PessoaMock.COD_PESSOA)).thenReturn(pessoaEnderecos);

		// executa o método
		cadastroEnderecoService.removePessoa(PessoaMock.COD_PESSOA);

		// Validaçoes
		verify(pessoaEnderecoRepository, times(0)).delete(anyListOf(PessoaEndereco.class));
		verify(pessoaRepository, times(1)).delete(pessoa);
	}

	/**
	 * Junit para a remoção dos dados de pessoa
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 */
	@Test(expected = NenhumaPessoaEncontradaException.class)
	public void testRemovePessoaPessoaNaoEncontrada() throws NenhumaPessoaEncontradaException {

		// Mocks.
		final Pessoa pessoa = null;

		// Eventos
		when(pessoaRepository.findOne(PessoaMock.COD_PESSOA)).thenReturn(pessoa);

		// executa o método
		cadastroEnderecoService.removePessoa(PessoaMock.COD_PESSOA);
	}

	/**
	 * Junit para a remoção de um endereço.
	 * 
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 */
	@Test
	public void testRemoveEndereco() throws NenhumaPessoaEnderecoEncontradaException {

		// Mocks
		final PessoaEndereco pessoaEndereco = PessoaEnderecoMock.createMock();

		// Eventos
		when(pessoaEnderecoRepository.findOne(PessoaEnderecoMock.COD_PESSOA_ENDERECO)).thenReturn(pessoaEndereco);

		// executa o método
		cadastroEnderecoService.removeEndereco(PessoaEnderecoMock.COD_PESSOA_ENDERECO);

		// verificaçao
		verify(pessoaEnderecoRepository, times(1)).delete(pessoaEndereco);

	}

	/**
	 * Junit para a remoção de um endereço, sem nenhum endereço encontrado
	 * 
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 */
	@Test(expected = NenhumaPessoaEnderecoEncontradaException.class)
	public void testRemoveEnderecoSemEndereco() throws NenhumaPessoaEnderecoEncontradaException {

		// Mocks
		final PessoaEndereco pessoaEndereco = null;

		// Eventos
		when(pessoaEnderecoRepository.findOne(PessoaEnderecoMock.COD_PESSOA_ENDERECO)).thenReturn(pessoaEndereco);

		// executa o método
		cadastroEnderecoService.removeEndereco(PessoaEnderecoMock.COD_PESSOA_ENDERECO);

	}

	/**
	 * Junit para a busca dos dados de Pessoa.
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 */
	@Test
	public void testBuscaPessoaByCodPessoa() throws NenhumaPessoaEncontradaException {

		// Mocks.
		final Pessoa pessoa = PessoaMock.createMock();
		final List<PessoaEndereco> pessoaEnderecos = PessoaEnderecoMock.createMockList();

		// Eventos
		when(pessoaRepository.findOne(PessoaMock.COD_PESSOA)).thenReturn(pessoa);
		when(pessoaEnderecoRepository.findByCodPessoa(PessoaMock.COD_PESSOA)).thenReturn(pessoaEnderecos);

		// executa o método
		final PessoaTO pessoaTO = cadastroEnderecoService.buscaPessoaByCodPessoa(PessoaMock.COD_PESSOA);

		// Validaçoes
		Assert.assertEquals(PessoaMock.COD_PESSOA, pessoaTO.getCodPessoa());
		Assert.assertEquals(PessoaMock.NOM_PESSOA, pessoaTO.getNomPessoa());

		// Validações
		Assert.assertNotNull(pessoaTO.getPessoaEnderecosTO());

		final PessoaEnderecoTO pessoaEnderecoTO = pessoaTO.getPessoaEnderecosTO().get(0);
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
	 * Junit para a busca dos dados de Pessoa, sem nenhum endereço cadastrado
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 */
	@Test
	public void testBuscaPessoaByCodPessoaSemEndereco() throws NenhumaPessoaEncontradaException {

		// Mocks.
		final Pessoa pessoa = PessoaMock.createMock();
		final List<PessoaEndereco> pessoaEnderecos = null;

		// Eventos
		when(pessoaRepository.findOne(PessoaMock.COD_PESSOA)).thenReturn(pessoa);
		when(pessoaEnderecoRepository.findByCodPessoa(PessoaMock.COD_PESSOA)).thenReturn(pessoaEnderecos);

		// executa o método
		final PessoaTO pessoaTO = cadastroEnderecoService.buscaPessoaByCodPessoa(PessoaMock.COD_PESSOA);

		// Validaçoes
		Assert.assertEquals(PessoaMock.COD_PESSOA, pessoaTO.getCodPessoa());
		Assert.assertEquals(PessoaMock.NOM_PESSOA, pessoaTO.getNomPessoa());

		// Validações
		Assert.assertNull(pessoaTO.getPessoaEnderecosTO());

	}

	/**
	 * Junit para a busca dos dados de Pessoa, Sem encontrar nenhuma pessoa
	 * 
	 * @throws NenhumaPessoaEncontradaException
	 */
	@Test(expected = NenhumaPessoaEncontradaException.class)
	public void testBuscaPessoaByCodPessoaSemPessoa() throws NenhumaPessoaEncontradaException {

		// Mocks.
		final Pessoa pessoa = null;

		// Eventos
		when(pessoaRepository.findOne(PessoaMock.COD_PESSOA)).thenReturn(pessoa);

		// executa o método
		cadastroEnderecoService.buscaPessoaByCodPessoa(PessoaMock.COD_PESSOA);

	}

	/**
	 * Junit para o método de busca de endereços através do código do endereço
	 * 
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 */
	@Test
	public void testBuscaEnderecoByCodEndereco() throws NenhumaPessoaEnderecoEncontradaException {

		// Mocks.
		final PessoaEndereco pessoaEndereco = PessoaEnderecoMock.createMock();

		// Eventos
		when(pessoaEnderecoRepository.findOne(PessoaEnderecoMock.COD_PESSOA_ENDERECO)).thenReturn(pessoaEndereco);

		// executa o método
		final PessoaEnderecoTO pessoaEnderecoTO = cadastroEnderecoService
				.buscaEnderecoByCodEndereco(PessoaEnderecoMock.COD_PESSOA_ENDERECO);

		// Verificaçao
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
	 * Junit para o método de busca de endereços através do código do endereço
	 * 
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 */
	@Test(expected = NenhumaPessoaEnderecoEncontradaException.class)
	public void testBuscaEnderecoByCodEnderecoSemEndereco() throws NenhumaPessoaEnderecoEncontradaException {

		// Mocks.
		final PessoaEndereco pessoaEndereco = null;

		// Eventos
		when(pessoaEnderecoRepository.findOne(PessoaEnderecoMock.COD_PESSOA_ENDERECO)).thenReturn(pessoaEndereco);

		// executa o método
		cadastroEnderecoService.buscaEnderecoByCodEndereco(PessoaEnderecoMock.COD_PESSOA_ENDERECO);

	}

}
