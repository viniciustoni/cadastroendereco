package br.com.cadastroendereco.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import br.com.cadastroendereco.entity.PessoaEndereco;
import br.com.cadastroendereco.to.PessoaEnderecoTO;

/**
 * Métodos de conversão para a entidade de {@link PessoaEnderecoTO}.
 * 
 * @author Vinicius A Gai
 *
 */
public abstract class PessoaEnderecoTOConverter {

	/**
	 * Converter do objeto de {@link PessoaEndereco} para o objeto de
	 * {@link PessoaEnderecoTO}.
	 * 
	 * @param pessoaEndereco
	 *            Dados do endereço.
	 * @return Dados de {@link PessoaEnderecoTO}
	 */
	public static PessoaEnderecoTO converterPessoaEnderecoToPessoaEnderecoTO(final PessoaEndereco pessoaEndereco) {

		PessoaEnderecoTO pessoaEnderecoTO = null;

		if (pessoaEndereco != null) {

			pessoaEnderecoTO = new PessoaEnderecoTO();

			pessoaEnderecoTO.setCodPessoaEndereco(pessoaEndereco.getCodPessoaEndereco());
			pessoaEnderecoTO.setNomLogradouro(pessoaEndereco.getNomLogradouro());
			pessoaEnderecoTO.setNumLogradouro(pessoaEndereco.getNumLogradouro());
			pessoaEnderecoTO.setDscComplemento(pessoaEndereco.getDscComplemento());
			pessoaEnderecoTO.setNumCep(pessoaEndereco.getNumCep());
			pessoaEnderecoTO.setNomBairro(pessoaEndereco.getNomBairro());
			pessoaEnderecoTO.setNomCidade(pessoaEndereco.getNomCidade());
			pessoaEnderecoTO.setSglUf(pessoaEndereco.getSglUf());
		}

		return pessoaEnderecoTO;
	}

	/**
	 * Converter uma lista de {@link PessoaEndereco} para uma lista de
	 * {@link PessoaEnderecoTO}.
	 * 
	 * @param pessoaEnderecos
	 *            Dados do endereço.
	 * @return Dados de {@link PessoaEnderecoTO}
	 */
	public static List<PessoaEnderecoTO> converterPessoaEnderecoListToPessoaEnderecoTO(
			final List<PessoaEndereco> pessoaEnderecos) {

		List<PessoaEnderecoTO> pessoaEnderecosTO = null;

		if (CollectionUtils.isNotEmpty(pessoaEnderecos)) {

			pessoaEnderecosTO = new ArrayList<>();

			// Efetua a conversão
			for (PessoaEndereco pessoaEndereco : pessoaEnderecos) {
				pessoaEnderecosTO.add(converterPessoaEnderecoToPessoaEnderecoTO(pessoaEndereco));
			}
		}

		return pessoaEnderecosTO;
	}

}
