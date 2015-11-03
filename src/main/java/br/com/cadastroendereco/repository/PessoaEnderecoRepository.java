package br.com.cadastroendereco.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.cadastroendereco.entity.PessoaEndereco;

/**
 * Repositório para a classe de {@link PessoaEndereco}
 * 
 * @author Vinicius A Gai
 *
 */
public interface PessoaEnderecoRepository extends CrudRepository<PessoaEndereco, Long> {

	/**
	 * Busca pelos dados do endereço através do código da pessoa.
	 * 
	 * @param codPessoa
	 *            Código da pessoa.
	 * @return Lista de Endereços.
	 */
	List<PessoaEndereco> findByCodPessoa(final Long codPessoa);

	/**
	 * Busca pelos dados do endereço através do código da pessoa e do endereço.
	 * 
	 * @param codPessoaEndereco
	 *            Código do endereço
	 * @param codPessoa
	 *            Código da pessoa.
	 * @return Endereço.
	 */
	PessoaEndereco findByCodPessoaEnderecoAndCodPessoa(final Long codPessoaEndereco, final Long codPessoa);

}
