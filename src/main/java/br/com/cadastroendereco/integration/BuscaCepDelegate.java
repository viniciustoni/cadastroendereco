package br.com.cadastroendereco.integration;

import br.com.cadastroendereco.integration.exception.BuscaCepException;
import br.com.cadastroendereco.integration.exception.CepInvalidoException;
import br.com.cadastroendereco.integration.exception.NenhumCepEncontradoException;
import br.com.cadastroendereco.integration.to.CepTO;

/**
 * Declaraçao dos métodos de client para o serviço de BuscaCep.
 * 
 * @author Vinicius A Gai
 *
 */
public interface BuscaCepDelegate {

	/**
	 * Busca pelos dados de CEP.
	 * 
	 * @param numCep
	 *            Número do CEP.
	 * @return Dados do Cep.
	 * @throws CepInvalidoException Caso CEP Inválido
	 * @throws NenhumCepEncontradoException Caso nenhum endereço localizado para o CEP informado.
	 * @throws BuscaCepException Caso problemas na consulta de CEP.
	 */
	CepTO buscaCep(final String numCep) throws CepInvalidoException, NenhumCepEncontradoException, BuscaCepException;

}
