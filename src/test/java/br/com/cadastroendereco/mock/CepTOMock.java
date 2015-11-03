package br.com.cadastroendereco.mock;

import br.com.cadastroendereco.integration.to.CepTO;

/**
 * Classe de criaçao de objeto de mock para o objeto de {@link CepTO}
 * 
 * @author Vinicius A Gai
 */
public class CepTOMock {

	public final static String NUM_CEP = "06213040";
	public final static String NOM_LOGRADOURO = "Rua zuma de sá fernandes";
	public final static String NOM_CIDADE = "Osasco";
	public final static String SGL_UF = "SP";
	public final static String NOM_BAIRRO = "Presidente Altino";

	/**
	 * Cria o objeto de mock para {@link CepTO}
	 * 
	 * @return Objeto de {@link CepTO}
	 */
	public static CepTO createMock() {
		final CepTO cepTO = new CepTO();

		cepTO.setNumCep(NUM_CEP);
		cepTO.setNomLogradouro(NOM_LOGRADOURO);
		cepTO.setNomCidade(NOM_CIDADE);
		cepTO.setNomBairro(NOM_BAIRRO);
		cepTO.setSglUf(SGL_UF);

		return cepTO;
	}
}
