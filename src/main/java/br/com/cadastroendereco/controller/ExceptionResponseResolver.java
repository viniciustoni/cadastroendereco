package br.com.cadastroendereco.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cadastroendereco.exception.NenhumaPessoaEncontradaException;
import br.com.cadastroendereco.exception.NenhumaPessoaEnderecoEncontradaException;
import br.com.cadastroendereco.integration.exception.CepInvalidoException;

/**
 * Configura as exceptions para o servico de busca cep.
 * 
 * @author Vinicius A Gai
 *
 */
@ControllerAdvice
public class ExceptionResponseResolver {

	public final static int NENHUMA_PESSOA_ENCONTRADA_EXCEPTION = 430;
	public final static int NENHUMA_PESSOA_ENDERECO_ENCONTRADA_EXCEPTION = 431;
	public final static int CEP_INVALIDO_EXCEPTION = 432;
	
	@ExceptionHandler(NenhumaPessoaEncontradaException.class)
	public void configureNenhumaPessoaEncontradaException(Exception exception, HttpServletResponse response)
			throws IOException {
		response.setStatus(NENHUMA_PESSOA_ENCONTRADA_EXCEPTION);
		IOUtils.write(exception.getMessage(), response.getOutputStream());
	}
	
	@ExceptionHandler(NenhumaPessoaEnderecoEncontradaException.class)
	public void configureNenhumaPessoaEnderecoEncontradaException(Exception exception, HttpServletResponse response)
			throws IOException {
		response.setStatus(NENHUMA_PESSOA_ENDERECO_ENCONTRADA_EXCEPTION);
		IOUtils.write(exception.getMessage(), response.getOutputStream());
	}
	
	@ExceptionHandler(CepInvalidoException.class)
	public void configureCepInvalidoException(Exception exception, HttpServletResponse response)
			throws IOException {
		response.setStatus(CEP_INVALIDO_EXCEPTION);
		IOUtils.write(exception.getMessage(), response.getOutputStream());
	}
}
