package br.com.cadastroendereco.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastroendereco.converter.PessoaConverter;
import br.com.cadastroendereco.converter.PessoaEnderecoConverter;
import br.com.cadastroendereco.converter.PessoaEnderecoTOConverter;
import br.com.cadastroendereco.converter.PessoaTOConverter;
import br.com.cadastroendereco.entity.Pessoa;
import br.com.cadastroendereco.entity.PessoaEndereco;
import br.com.cadastroendereco.exception.NenhumaPessoaEncontradaException;
import br.com.cadastroendereco.exception.NenhumaPessoaEnderecoEncontradaException;
import br.com.cadastroendereco.integration.BuscaCepDelegate;
import br.com.cadastroendereco.integration.exception.BuscaCepException;
import br.com.cadastroendereco.integration.exception.CepInvalidoException;
import br.com.cadastroendereco.integration.exception.NenhumCepEncontradoException;
import br.com.cadastroendereco.repository.PessoaEnderecoRepository;
import br.com.cadastroendereco.repository.PessoaRepository;
import br.com.cadastroendereco.service.CadastroEnderecoService;
import br.com.cadastroendereco.to.PessoaEnderecoTO;
import br.com.cadastroendereco.to.PessoaTO;

/**
 * Implementação dos serviços de cadastro de endereços.
 * 
 * @author Vinicius A Gai
 */
@Service
public class CadastroEnderecoServiceImpl implements CadastroEnderecoService {

	private static final Logger logger = LoggerFactory.getLogger(CadastroEnderecoServiceImpl.class);

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaEnderecoRepository pessoaEnderecoRepository;

	@Autowired
	private BuscaCepDelegate buscaCepDelegate;

	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public PessoaTO gravaEndereco(final PessoaTO pessoaTO)
			throws NenhumaPessoaEncontradaException, NenhumaPessoaEnderecoEncontradaException, CepInvalidoException {

		// Validação
		Validate.notNull(pessoaTO, "Os dados de endereço devem ser informados.");

		// Valida alteraçao.
		Pessoa pessoa = null;
		if (pessoaTO.getCodPessoa() != null) {
			buscaPessoaByCodPessoa(pessoaTO.getCodPessoa());
		} else {
			pessoa = pessoaRepository.findByNomPessoaIgnoreCase(pessoaTO.getNomPessoa());
			if (pessoa != null) {
				pessoaTO.setCodPessoa(pessoa.getCodPessoa());
			}
		}
		
		// Converter para os dados da pessoa
		pessoa = PessoaConverter.converterPessoaTOToPessoa(pessoaTO);

		// Salva os dados da pessoa.
		pessoa = pessoaRepository.save(pessoa);
		final PessoaTO pessoaTOReturn = PessoaTOConverter.converterPessoaToPessoaTO(pessoa);
		
		// grava os dados de endereço da pessoa.
		final List<PessoaEnderecoTO> pessoaEnderecosTOSaved = gravaDadosEndereco(pessoa, pessoaTO.getPessoaEnderecosTO());
		pessoaTOReturn.setPessoaEnderecosTO(pessoaEnderecosTOSaved);
		
		// retorna os dados
		return pessoaTOReturn;

	}

	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public void removePessoa(final Long codPessoa) throws NenhumaPessoaEncontradaException {

		// busca pela pessoa
		final Pessoa pessoa = pessoaRepository.findOne(codPessoa);

		// Caso nao encontrou a pessoa lança exception
		if (pessoa == null) {
			throw new NenhumaPessoaEncontradaException(
					MessageFormat.format("Nenhuma pessoa encontrada para o código {0}.", codPessoa));
		}

		// Busca pelos dados do endereço
		final List<PessoaEndereco> pessoaEnderecos = pessoaEnderecoRepository.findByCodPessoa(codPessoa);

		// Caso encontre endereço apaga os mesmos.
		if (CollectionUtils.isNotEmpty(pessoaEnderecos)) {
			pessoaEnderecoRepository.delete(pessoaEnderecos);
		}

		// Apaga a pessoa
		pessoaRepository.delete(pessoa);

	}

	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public void removeEndereco(final Long codPessoaEndereco) throws NenhumaPessoaEnderecoEncontradaException {
		// Busca pelos dados do endereço
		final PessoaEndereco pessoaEndereco = pessoaEnderecoRepository.findOne(codPessoaEndereco);

		if (pessoaEndereco == null) {
			throw new NenhumaPessoaEnderecoEncontradaException(
					MessageFormat.format("Nenhum endereço encontrada para o código {0}.", codPessoaEndereco));
		}

		// Apaga o endereço
		pessoaEnderecoRepository.delete(pessoaEndereco);
	}

	@Override
	public PessoaTO buscaPessoaByCodPessoa(final Long codPessoa) throws NenhumaPessoaEncontradaException {

		// busca pela pessoa
		final Pessoa pessoa = pessoaRepository.findOne(codPessoa);

		// Caso nao encontrou a pessoa lança exception
		if (pessoa == null) {
			throw new NenhumaPessoaEncontradaException(
					MessageFormat.format("Nenhuma pessoa encontrada para o código {0}.", codPessoa));
		}

		// Converter para PessoaTO
		final PessoaTO pessoaTO = PessoaTOConverter.converterPessoaToPessoaTO(pessoa);

		// Busca pelos dados do endereço
		final List<PessoaEndereco> pessoaEnderecos = pessoaEnderecoRepository.findByCodPessoa(codPessoa);

		// Caso encontre endereço apaga os mesmos.
		if (CollectionUtils.isNotEmpty(pessoaEnderecos)) {
			pessoaTO.setPessoaEnderecosTO(
					PessoaEnderecoTOConverter.converterPessoaEnderecoListToPessoaEnderecoTO(pessoaEnderecos));
		}

		return pessoaTO;
	}

	@Override
	public PessoaEnderecoTO buscaEnderecoByCodEndereco(final Long codPessoaEndereco)
			throws NenhumaPessoaEnderecoEncontradaException {

		// Busca pelos dados do endereço
		final PessoaEndereco pessoaEndereco = pessoaEnderecoRepository.findOne(codPessoaEndereco);

		if (pessoaEndereco == null) {
			throw new NenhumaPessoaEnderecoEncontradaException(
					MessageFormat.format("Nenhum endereço encontrada para o código {0}.", codPessoaEndereco));
		}

		return PessoaEnderecoTOConverter.converterPessoaEnderecoToPessoaEnderecoTO(pessoaEndereco);
	}
	
	/**
	 * Grava os dados de endereço.
	 * 
	 * @param pessoa
	 *            Dados da Pessoa.
	 * @param pessoaEnderecosTO
	 * @return Dados do endereço salvo
	 * @throws CepInvalidoException
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 */
	private List<PessoaEnderecoTO> gravaDadosEndereco(final Pessoa pessoa, final List<PessoaEnderecoTO> pessoaEnderecosTO)
			throws CepInvalidoException, NenhumaPessoaEnderecoEncontradaException {

		PessoaEndereco pessoaEndereco = null;
		List<PessoaEnderecoTO> pessoaEnderecosTOSaved = null;

		if (CollectionUtils.isNotEmpty(pessoaEnderecosTO)) {

			pessoaEnderecosTOSaved = new ArrayList<>();
			
			// Percorre a lista e grava os dados de endereço.
			for (PessoaEnderecoTO pessoaEnderecoTO : pessoaEnderecosTO) {

				try {
					buscaCepDelegate.buscaCep(pessoaEnderecoTO.getNumCep());
				} catch (NenhumCepEncontradoException e) {
					logger.error("Erro ao buscar pelo cep.", e);
					throw new CepInvalidoException("CEP Inválido", e);
				} catch (BuscaCepException e) {
					logger.error("Erro ao buscar pelo cep.", e);
					throw new CepInvalidoException("CEP Inválido", e);
				}

				// Caso o código do endereço for informado, valida se el existe
				// e é para a pessoa informada.
				if (pessoaEnderecoTO.getCodPessoaEndereco() != null) {
					pessoaEndereco = pessoaEnderecoRepository.findByCodPessoaEnderecoAndCodPessoa(
							pessoaEnderecoTO.getCodPessoaEndereco(), pessoa.getCodPessoa());
					// Caso nao exista o ID para o cliente, será lançado uma
					// exception.
					if (pessoaEndereco == null) {
						throw new NenhumaPessoaEnderecoEncontradaException(
								MessageFormat.format("Nenhum endereço encontrada para o código {0}.",
										pessoaEnderecoTO.getCodPessoaEndereco()));
					}

				}

				// Converter para o endereço
				pessoaEndereco = PessoaEnderecoConverter
						.converterPessoaEnderecoTOAndPessoaTOToPessoaEndereco(pessoaEnderecoTO, pessoa.getCodPessoa());
				pessoaEnderecoRepository.save(pessoaEndereco);
				
				// Monta o objeto de retorno.
				pessoaEnderecosTOSaved.add(PessoaEnderecoTOConverter.converterPessoaEnderecoToPessoaEnderecoTO(pessoaEndereco));
			}

		}
		
		return pessoaEnderecosTOSaved;

	}

}
