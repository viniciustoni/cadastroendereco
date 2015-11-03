package br.com.cadastroendereco.integration.exception;

/**
 * Caso nenhum cep localizado.
 * 
 * @author Vinicius A Gai
 *
 */
public class NenhumCepEncontradoException extends Exception {

	private static final long serialVersionUID = -1730668488476454686L;

	public NenhumCepEncontradoException() {
		super();
	}

	public NenhumCepEncontradoException(String message) {
		super(message);
	}

}
