package br.com.cadastroendereco.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.cadastroendereco.entity.Pessoa;
import br.com.cadastroendereco.entity.PessoaEndereco;

/**
 * Classe de criaçao de mock para a entidade de {@link PessoaEndereco}
 * 
 * @author Vinicius A Gai
 */
public final class PessoaEnderecoMock {
	
	public static final Long COD_PESSOA_ENDERECO = 1l;
	public static final Long COD_PESSOA = PessoaMock.COD_PESSOA;
	public static final Pessoa PESSOA = PessoaMock.createMock();
	public static final String NUM_CEP = "06213040";
	public static final String NOM_LOGRADOURO = "Rua Zuma de Sa Fernandes";
	public static final String NUM_LOGRADOURO = "323";
	public static final String DSC_COMPLEMENTO = "APT 71 TR 1";
	public static final String NOM_CIDADE = "Osasco";
	public static final String SGL_UF = "SP";
	public static final String NOM_BAIRRO = "Presidente Altino";
	
	/**
	 * Cria o objeto de mock para a {@link PessoaEndereco}
	 * 
	 * @return Dados da {@link PessoaEndereco} para mock.
	 */
	public static PessoaEndereco createMock() {
		final PessoaEndereco pessoaEndereco = new PessoaEndereco();
		
		pessoaEndereco.setCodPessoaEndereco(COD_PESSOA_ENDERECO);
		pessoaEndereco.setCodPessoa(COD_PESSOA);
		pessoaEndereco.setPessoa(PESSOA);
		pessoaEndereco.setNumCep(NUM_CEP);
		pessoaEndereco.setNomLogradouro(NOM_LOGRADOURO);
		pessoaEndereco.setNumLogradouro(NUM_LOGRADOURO);
		pessoaEndereco.setDscComplemento(DSC_COMPLEMENTO);
		pessoaEndereco.setNomCidade(NOM_CIDADE);
		pessoaEndereco.setSglUf(SGL_UF);
		pessoaEndereco.setNomBairro(NOM_BAIRRO);
		
		return pessoaEndereco;
	}
	
	/**
	 * Cria uma lista de {@link PessoaEndereco}.
	 * 
	 * @return Lista de {@link PessoaEndereco}
	 */
	public static List<PessoaEndereco> createMockList() {
		
		final List<PessoaEndereco> pessoaEnderecos = new ArrayList<>();
		
		pessoaEnderecos.add(createMock());
		
		return pessoaEnderecos;
		
	}
	
}
