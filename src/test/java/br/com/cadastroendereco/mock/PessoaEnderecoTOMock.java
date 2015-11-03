package br.com.cadastroendereco.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.cadastroendereco.to.PessoaEnderecoTO;

/**
 * Classe de criaçao de mock para a entidade de {@link PessoaEnderecoTO}
 * 
 * @author Vinicius A Gai
 */
public final class PessoaEnderecoTOMock {
	
	public static final Long COD_PESSOA_ENDERECO = 1l;
	public static final Long COD_PESSOA = PessoaTOMock.COD_PESSOA;
	public static final String NUM_CEP = "06213040";
	public static final String NOM_LOGRADOURO = "Rua Zuma de Sa Fernandes";
	public static final String NUM_LOGRADOURO = "323";
	public static final String DSC_COMPLEMENTO = "APT 71 TR 1";
	public static final String NOM_CIDADE = "Osasco";
	public static final String SGL_UF = "SP";
	public static final String NOM_BAIRRO = "Presidente Altino";
	
	/**
	 * Cria o objeto de mock para a {@link PessoaEnderecoTO}
	 * 
	 * @return Dados da {@link PessoaEnderecoTO} para mock.
	 */
	public static PessoaEnderecoTO createMock() {
		final PessoaEnderecoTO pessoaEnderecoTO = new PessoaEnderecoTO();
		
		pessoaEnderecoTO.setCodPessoaEndereco(COD_PESSOA_ENDERECO);
		pessoaEnderecoTO.setNumCep(NUM_CEP);
		pessoaEnderecoTO.setNomLogradouro(NOM_LOGRADOURO);
		pessoaEnderecoTO.setNumLogradouro(NUM_LOGRADOURO);
		pessoaEnderecoTO.setDscComplemento(DSC_COMPLEMENTO);
		pessoaEnderecoTO.setNomCidade(NOM_CIDADE);
		pessoaEnderecoTO.setSglUf(SGL_UF);
		pessoaEnderecoTO.setNomBairro(NOM_BAIRRO);
		
		return pessoaEnderecoTO;
	}
	
	/**
	 * Cria uma lista de {@link PessoaEnderecoTO}.
	 * 
	 * @return Lista de {@link PessoaEnderecoTO}
	 */
	public static List<PessoaEnderecoTO> createMockList() {
		
		final List<PessoaEnderecoTO> pessoaEnderecosTO = new ArrayList<>();
		
		pessoaEnderecosTO.add(createMock());
		
		return pessoaEnderecosTO;
		
	}
	
}
