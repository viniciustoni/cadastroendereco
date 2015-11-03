package br.com.cadastroendereco.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cadastroendereco.entity.Pessoa;

/**
 * Repositório para a classe de Pessoa.
 * 
 * @author Vinicius A Gai
 *
 */
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{

	/**
	 * Busca pela pessoa através do nome.
	 * 
	 * @param nomPessoa Nome da pessoa.
	 * @return Pessoa.
	 */
	Pessoa findByNomPessoaIgnoreCase(final String nomPessoa);
	
}
