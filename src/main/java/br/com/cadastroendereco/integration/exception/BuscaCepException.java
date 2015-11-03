package br.com.cadastroendereco.integration.exception;

/**
 * Exception para o serviço de busca de CEP.
 * 
 * @author Vinicius A Gai
 *
 */
public class BuscaCepException extends Exception {

	private static final long serialVersionUID = -4301091537341664484L;

	public BuscaCepException() {
		super();
	}

	public BuscaCepException(String message) {
		super(message);
	}

}
