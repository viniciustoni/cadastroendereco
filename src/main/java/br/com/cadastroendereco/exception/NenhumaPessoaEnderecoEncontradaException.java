package br.com.cadastroendereco.exception;

/**
 * Exception para caso nenhum endereço encontrado.
 * 
 * @author Vinicius A Gai
 *
 */
public class NenhumaPessoaEnderecoEncontradaException extends Exception {

	private static final long serialVersionUID = 5021684599103303317L;

	public NenhumaPessoaEnderecoEncontradaException() {
		super();
	}

	public NenhumaPessoaEnderecoEncontradaException(String message) {
		super(message);
	}

}
