package br.com.cadastroendereco.integration.exception;

/**
 * Classe de exception lançado caso o CEP esteja inválido.
 * 
 * @author Vinicius A Gai
 *
 */
public class CepInvalidoException extends Exception {

	private static final long serialVersionUID = 8375173359489174325L;

	public CepInvalidoException() {
		super();
	}

	public CepInvalidoException(String message) {
		super(message);
	}

	public CepInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CepInvalidoException(Throwable cause) {
		super(cause);
	}

}
