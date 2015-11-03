package br.com.cadastroendereco.mock;

import java.util.List;

import br.com.cadastroendereco.to.PessoaEnderecoTO;
import br.com.cadastroendereco.to.PessoaTO;

/**
 * Classe para gerar o Mock para a entidade de PessoaTO.
 * 
 * @author Vinicius A Gai
 *
 */
public final class PessoaTOMock {

	public static final Long COD_PESSOA = 1l;
	public static final String NOM_PESSOA = "Vinicius A Gai";
	public static final List<PessoaEnderecoTO> PESSOA_ENDERECOS_LIST = PessoaEnderecoTOMock.createMockList();

	/**
	 * Cria o objeto de Mock para a PessoaTO.
	 * 
	 * @return Dados da PessoaTO.
	 */
	public static PessoaTO createMock() {
		final PessoaTO pessoaTO = new PessoaTO();

		pessoaTO.setCodPessoa(COD_PESSOA);
		pessoaTO.setNomPessoa(NOM_PESSOA);
		pessoaTO.setPessoaEnderecosTO(PESSOA_ENDERECOS_LIST);

		return pessoaTO;
	}
	
	/**
	 * Cria o objeto de Mock para a PessoaTO.
	 * 
	 * @return Dados da PessoaTO.
	 */
	public static PessoaTO createMock(final Long codPessoa) {
		final PessoaTO pessoaTO = new PessoaTO();

		pessoaTO.setCodPessoa(codPessoa);
		pessoaTO.setNomPessoa(NOM_PESSOA);
		pessoaTO.setPessoaEnderecosTO(PESSOA_ENDERECOS_LIST);

		return pessoaTO;
	}

}
