package br.com.cadastroendereco.service;

import br.com.cadastroendereco.exception.NenhumaPessoaEncontradaException;
import br.com.cadastroendereco.exception.NenhumaPessoaEnderecoEncontradaException;
import br.com.cadastroendereco.integration.exception.CepInvalidoException;
import br.com.cadastroendereco.to.PessoaEnderecoTO;
import br.com.cadastroendereco.to.PessoaTO;

/**
 * Declaraçao dos métodos para o Cadastro de endereço.
 * 
 * @author Vinicius A Gai
 *
 */
public interface CadastroEnderecoService {

	/**
	 * Grava os dados de endereço e de pessoa, onde: <br>
	 * <ul>
	 * <li>Caso codPessoa == null: Busca pelo nome da pessoa na base, se
	 * encontrar cadastra o endereço para ela, caso contrário grava uma nova
	 * pessoa</li>
	 * <li>Caso codPessoa != null: Verifica se o ID existe, se existir grava o
	 * endereço para a pessoa, caso contrario, lança exception.</li>
	 * </ul>
	 * 
	 * @param pessoaTO
	 *            Dados da pessoa.
	 * @return Dado da pessoa salvo
	 * @throws NenhumaPessoaEncontradaException
	 *             Caso nenhuma pessoa localizada para o ID informado.
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 *             Caso nenhum endereço seja localizado
	 * @throws CepInvalidoException
	 *             Caso CEP inválido.
	 */
	PessoaTO gravaEndereco(final PessoaTO pessoaTO)
			throws NenhumaPessoaEncontradaException, NenhumaPessoaEnderecoEncontradaException, CepInvalidoException;

	/**
	 * Remove os dados da Pessoa e todos os seus endereços.
	 * 
	 * @param codPessoa
	 *            Código da pessoa.
	 * @throws NenhumaPessoaEncontradaException
	 *             Caso nenhuma pessoa localizada para o ID informado.
	 */
	void removePessoa(final Long codPessoa) throws NenhumaPessoaEncontradaException;

	/**
	 * Remove os dados do Endereço.
	 * 
	 * @param codPessoaEndereco
	 *            Código do endereço.
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 *             Caso nenhum endereço localizada para o ID informado.
	 */
	void removeEndereco(final Long codPessoaEndereco) throws NenhumaPessoaEnderecoEncontradaException;

	/**
	 * Busca pelos dados da Pessoa e seus endereços.
	 * 
	 * @param codPessoa
	 *            Código da pessoa
	 * @return Dados da Pessoa.
	 * @throws NenhumaPessoaEncontradaException
	 *             Caso nenhuma pessoa localizada para o ID informado.
	 */
	PessoaTO buscaPessoaByCodPessoa(final Long codPessoa) throws NenhumaPessoaEncontradaException;

	/**
	 * Busca pelos dados de um determinado endereço.
	 * 
	 * @param codPessoaEndereco
	 *            Código do endereço.
	 * @return Dados do endereço.
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 *             Caso nenhum endereço localizada para o ID informado.
	 */
	PessoaEnderecoTO buscaEnderecoByCodEndereco(final Long codPessoaEndereco)
			throws NenhumaPessoaEnderecoEncontradaException;

}
