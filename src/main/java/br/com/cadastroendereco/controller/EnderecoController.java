package br.com.cadastroendereco.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import br.com.cadastroendereco.exception.NenhumaPessoaEncontradaException;
import br.com.cadastroendereco.exception.NenhumaPessoaEnderecoEncontradaException;
import br.com.cadastroendereco.integration.exception.CepInvalidoException;
import br.com.cadastroendereco.service.CadastroEnderecoService;
import br.com.cadastroendereco.to.PessoaEnderecoTO;
import br.com.cadastroendereco.to.PessoaTO;

/**
 * Controller para as operações de endereço.
 * 
 * @author Vinicius A Gai
 *
 */
@RestController
public class EnderecoController {

	private final Logger logger = LoggerFactory.getLogger(EnderecoController.class);

	@Autowired
	private CadastroEnderecoService cadastroEnderecoService;

	/**
	 * Grava/Altera os dados de endereço e de pessoa, onde: <br>
	 * <ul>
	 * <li>Caso codPessoa == null: Busca pelo nome da pessoa na base, se
	 * encontrar cadastra o endereço para ela, caso contrário grava uma nova
	 * pessoa</li>
	 * <li>Caso codPessoa != null: Verifica se o ID existe, se existir grava o
	 * endereço para a pessoa, caso contrario, lança exception.</li>
	 * <li>Caso codPessoaEndereço == null: Grava o novo endereço para pessoa
	 * </li>
	 * <li>Caso codPessoaEndereço != null: Verifica se o ID existe para o
	 * COD_PESSOA informado, se existir grava o endereço para a pessoa, caso
	 * contrario, lança exception.</li>
	 * </ul>
	 * 
	 * @param pessoaTO
	 *            Dados da pessoa.
	 * @throws NenhumaPessoaEncontradaException
	 *             Caso nenhuma pessoa localizada para o ID informado.
	 * @throws CepInvalidoException
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 */
	@RequestMapping(value = "/cadastroendereco/gravaEndereco", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Grava os dados do endereço", notes = "Grava/Altera os dados de endereço e de pessoa, onde: <br>"
			+ "<ul>" + "<li>Caso codPessoa == null: Busca pelo nome da pessoa na base, se"
			+ "encontrar cadastra o endereço para ela, caso contrário grava uma nova" + "pessoa</li>"
			+ "<li>Caso codPessoa != null: Verifica se o ID existe, se existir grava o"
			+ "endereço para a pessoa, caso contrario, lança exception.</li>"
			+ "<li>Caso codPessoaEndereço == null: Grava o novo endereço para pessoa</li>"
			+ "<li>Caso codPessoaEndereço != null: Verifica se o ID existe para o COD_PESSOA informado, se existir grava o"
			+ "endereço para a pessoa, caso contrario, lança exception.</li>" + "</ul>")
	@ApiResponses(value = { @ApiResponse(code = 200, response = PessoaTO.class, message = "Cadastro efetuado com sucesso"),
			@ApiResponse(code = ExceptionResponseResolver.NENHUMA_PESSOA_ENCONTRADA_EXCEPTION, message = "Nenhuma pessoa encontrada"),
			@ApiResponse(code = ExceptionResponseResolver.NENHUMA_PESSOA_ENDERECO_ENCONTRADA_EXCEPTION, message = "Nenhum endereço encontrada"),
			@ApiResponse(code = ExceptionResponseResolver.CEP_INVALIDO_EXCEPTION, message = "CEP Inválido") })
	public @ResponseBody PessoaTO gravaEndereco(@RequestBody @Valid final PessoaTO pessoaTO)
			throws NenhumaPessoaEncontradaException, NenhumaPessoaEnderecoEncontradaException, CepInvalidoException {
		logger.debug("gravaEndereco");
		return cadastroEnderecoService.gravaEndereco(pessoaTO);
	}

	/**
	 * Remove os dados da Pessoa e todos os seus endereços.
	 * 
	 * @param codPessoa
	 *            Código da pessoa.
	 * @throws NenhumaPessoaEncontradaException
	 *             Caso nenhuma pessoa localizada para o ID informado.
	 */
	@RequestMapping(value = "/cadastroendereco/removePessoa", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Remove a pessoa e todos os seus endereços da base de dados.", notes = "Remove a pessoa e todos os seus endereços da base de dados.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Remoção efetuado com sucesso"),
			@ApiResponse(code = ExceptionResponseResolver.NENHUMA_PESSOA_ENCONTRADA_EXCEPTION, message = "Nenhuma pessoa encontrada") })
	public @ResponseBody void removePessoa(
			@RequestBody @NotNull(message = "Código da pessoa é obrigatório.") final Long codPessoa)
					throws NenhumaPessoaEncontradaException {
		logger.debug("removePessoa");
		cadastroEnderecoService.removePessoa(codPessoa);
	}

	/**
	 * Remove os dados do Endereço.
	 * 
	 * @param codPessoaEndereco
	 *            Código do endereço.
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 *             Caso nenhum endereço localizada para o ID informado.
	 */
	@RequestMapping(value = "/cadastroendereco/removeEndereco", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Remove o endereço da base de dados.", notes = "Remove o endereço da base de dados.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Remoção efetuado com sucesso"),
			@ApiResponse(code = ExceptionResponseResolver.NENHUMA_PESSOA_ENDERECO_ENCONTRADA_EXCEPTION, message = "Nenhum endereço encontrada") })
	public @ResponseBody void removeEndereco(
			@RequestBody @NotNull(message = "Código do endereço é obrigatório.") final Long codPessoaEndereco)
					throws NenhumaPessoaEnderecoEncontradaException {
		logger.debug("removeEndereco");
		cadastroEnderecoService.removeEndereco(codPessoaEndereco);
	}

	/**
	 * Busca pelos dados da Pessoa e seus endereços.
	 * 
	 * @param codPessoa
	 *            Código da pessoa
	 * @return Dados da Pessoa.
	 * @throws NenhumaPessoaEncontradaException
	 *             Caso nenhuma pessoa localizada para o ID informado.
	 */
	@RequestMapping(value = "/cadastroendereco/buscaPessoaByCodPessoa", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Cadastra um cep na base de dados", notes = "Cadastra um cep na base de dados.")
	@ApiResponses(value = { @ApiResponse(code = 200, response = PessoaTO.class, message = "Busca efetuada com sucesso"),
			@ApiResponse(code = ExceptionResponseResolver.NENHUMA_PESSOA_ENCONTRADA_EXCEPTION, message = "Nenhuma pessoa encontrada") })
	@Transactional
	public @ResponseBody PessoaTO buscaPessoaByCodPessoa(
			@RequestBody @NotNull(message = "Código da pessoa é obrigatório.") final Long codPessoa)
					throws NenhumaPessoaEncontradaException {
		logger.debug("buscaPessoaByCodPessoa");
		return cadastroEnderecoService.buscaPessoaByCodPessoa(codPessoa);
	}

	/**
	 * Busca pelos dados de um determinado endereço.
	 * 
	 * @param codPessoaEndereco
	 *            Código do endereço.
	 * @return Dados do endereço.
	 * @throws NenhumaPessoaEnderecoEncontradaException
	 *             Caso nenhum endereço localizada para o ID informado.
	 */
	@RequestMapping(value = "/cadastroendereco/buscaEnderecoByCodEndereco", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Cadastra um cep na base de dados", notes = "Cadastra um cep na base de dados.")
	@ApiResponses(value = { @ApiResponse(code = 200, response = PessoaEnderecoTO.class, message = "Busca efetuada com sucesso"),
			@ApiResponse(code = ExceptionResponseResolver.NENHUMA_PESSOA_ENDERECO_ENCONTRADA_EXCEPTION, message = "Nenhum endereço encontrada") })
	@Transactional
	public @ResponseBody PessoaEnderecoTO buscaEnderecoByCodEndereco(
			@RequestBody @NotNull(message = "Código do endereço é obrigatório.") final Long codPessoaEndereco)
					throws NenhumaPessoaEnderecoEncontradaException {
		logger.debug("buscaEnderecoByCodEndereco");
		return cadastroEnderecoService.buscaEnderecoByCodEndereco(codPessoaEndereco);
	}

}
