package br.com.cadastroendereco.integration.impl;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.cadastroendereco.integration.BuscaCepDelegate;
import br.com.cadastroendereco.integration.exception.BuscaCepException;
import br.com.cadastroendereco.integration.exception.CepInvalidoException;
import br.com.cadastroendereco.integration.exception.NenhumCepEncontradoException;
import br.com.cadastroendereco.integration.to.CepTO;

/**
 * Efetua a implementação para o serviço de busca de CEP.
 * 
 * @author Vinicius A Gai
 */
@Component
public class BuscaCepDelegateImpl implements BuscaCepDelegate {
	
	private final static int NENHUM_CEP_ENCONTRADO_EXCEPTION = 430;
	private final static int CEP_INVALIDO_EXCEPTION = 431;

	private final Logger logger = LoggerFactory.getLogger(BuscaCepDelegateImpl.class);
	
	private static final String BUSCA_CEP_URL = "http://localhost:8181/buscacep/buscaCep/{numCep}";
	
	private RestTemplate restTemplate;

	/**
	 * Constructor.
	 */
	public BuscaCepDelegateImpl() {
		super();
		restTemplate = new RestTemplate();
	}
	
	@Override
	public CepTO buscaCep(String numCep) throws CepInvalidoException, NenhumCepEncontradoException, BuscaCepException {

		CepTO cepTO = null;
		
		try {
			cepTO = restTemplate.getForObject(BUSCA_CEP_URL, CepTO.class, numCep);
		} catch (HttpClientErrorException e) {
			logger.error("Erro na consulta de CEP.", e);
			
			switch (e.getStatusCode().value()) {
			case NENHUM_CEP_ENCONTRADO_EXCEPTION:
				logger.debug("Nenhum cep localizado");
				throw new NenhumCepEncontradoException(MessageFormat.format("Nenhum endereço foi localizado para o CEP: {0}", numCep));
			case CEP_INVALIDO_EXCEPTION:
				logger.debug("Cep Inválido");
				throw new CepInvalidoException(MessageFormat.format("O CEP {0} é inválido", numCep));
			default:
				logger.debug("Erro nao mapeado");
				throw new BuscaCepException(MessageFormat.format("Houve um erro não mapeado para a consulta do CEP {0}.", numCep));
			}
			
		}
		
		return cepTO;
	}
	
}
