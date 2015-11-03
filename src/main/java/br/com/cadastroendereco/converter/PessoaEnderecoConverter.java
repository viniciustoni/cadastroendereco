package br.com.cadastroendereco.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import br.com.cadastroendereco.entity.PessoaEndereco;
import br.com.cadastroendereco.to.PessoaEnderecoTO;

/**
 * Métodos de conversão para a entidade de {@link PessoaEndereco}.
 * 
 * @author Vinicius A Gai
 *
 */
public abstract class PessoaEnderecoConverter {

	/**
	 * Converter do objeto de {@link PessoaEnderecoTO} para o objeto de
	 * {@link PessoaEndereco}.
	 * 
	 * @param pessoaEnderecoTO
	 *            Dados do endereço.
	 * @return Dados de {@link PessoaEndereco}
	 */
	public static PessoaEndereco converterPessoaEnderecoTOAndPessoaTOToPessoaEndereco(
			final PessoaEnderecoTO pessoaEnderecoTO, final Long codPessoa) {

		PessoaEndereco pessoaEndereco = null;

		if (pessoaEnderecoTO != null) {

			pessoaEndereco = new PessoaEndereco();

			pessoaEndereco.setCodPessoaEndereco(pessoaEnderecoTO.getCodPessoaEndereco());
			pessoaEndereco.setCodPessoa(codPessoa);
			pessoaEndereco.setNomLogradouro(pessoaEnderecoTO.getNomLogradouro());
			pessoaEndereco.setNumLogradouro(pessoaEnderecoTO.getNumLogradouro());
			pessoaEndereco.setDscComplemento(pessoaEnderecoTO.getDscComplemento());
			pessoaEndereco.setNumCep(pessoaEnderecoTO.getNumCep());
			pessoaEndereco.setNomBairro(pessoaEnderecoTO.getNomBairro());
			pessoaEndereco.setNomCidade(pessoaEnderecoTO.getNomCidade());
			pessoaEndereco.setSglUf(pessoaEnderecoTO.getSglUf());
		}

		return pessoaEndereco;
	}

	/**
	 * Converter uma lista de {@link PessoaEnderecoTO} para uma lista de
	 * {@link PessoaEndereco}.
	 * 
	 * @param pessoaEnderecosTO
	 *            Dados do endereço.
	 * @return Dados de {@link PessoaEndereco}
	 */
	public static List<PessoaEndereco> converterPessoaEnderecoTOListAndPessoaTOToPessoaEndereco(
			final List<PessoaEnderecoTO> pessoaEnderecosTO, final Long codPessoa) {

		List<PessoaEndereco> pessoaEnderecos = null;

		if (CollectionUtils.isNotEmpty(pessoaEnderecosTO)) {

			pessoaEnderecos = new ArrayList<>();

			// Efetua a conversão
			for (PessoaEnderecoTO pessoaEnderecoTO : pessoaEnderecosTO) {
				pessoaEnderecos.add(converterPessoaEnderecoTOAndPessoaTOToPessoaEndereco(pessoaEnderecoTO, codPessoa));
			}
		}

		return pessoaEnderecos;
	}

}
