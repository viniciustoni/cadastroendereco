package br.com.cadastroendereco.exception;

/**
 * Exception para caso nenhum pessoa for localizada.
 * 
 * @author Vinicius A Gai
 *
 */
public class NenhumaPessoaEncontradaException extends Exception {

	private static final long serialVersionUID = -4636072879348497252L;

	public NenhumaPessoaEncontradaException() {
		super();
	}

	public NenhumaPessoaEncontradaException(String message) {
		super(message);
	}

}
