# cadastroendereco
Serviços para o cadastro de endereço de um determinado cliente

# Developers
- Vinicius Antonio Gai - viniciustoni@gmail.com

# Tecnologias
As técnologias utilizadas para o desenvolvimento da aplicação foram:
- Maven 3
- Spring Framework
- Spring boot
- Hibernate
- HSQLDB
- Swagger - Frameworks para API
- Java 7

#Arquitetura
A aplicação está divida em 3 camadas:
- Controller: Camada que está esposta para acesso externo, com os serviços REST.
- Service: Camada contendo o processamento da aplicação, ou seja, as regras de negócio.
- Model: Contendo as entidades de mapeamento do banco e classes de Repository para acesso a base de dados(consulta, save, update)
- Auxiliares: 
  - Pacote de converter para conversão dos TOs do serviço para as entities e vice-versa.
  - Pacote de delegate efetua a integração com os serviços de outros sistemas externos.


# Como executar a aplicação?

* IMPORTANTE - Esse projeto tem como premissa o projeto de BuscaCEP estar sendo executado na seguinte URL: http://localhost:8181/buscacep 
* URL para acesso a aplicação: https://github.com/viniciustoni/buscacep

Para rodar a aplicação é necessário conter o Maven 3 instalado e configurado na maquina.
Após isso basta executar no prompt, dentro da pasta da aplicação os seguintes comandos maven:
- Compilando a aplicação: mvn clean package
- Executando a aplicação: mvn spring-boot:run -Dserver.port=8080

# Como utilizar a aplicação?
Como a aplicação está executando o framework do Swagger, ao acessar a URL (http://localhost:8080/index.html) será redirecionado para uma pagina onde irá conter todos os serviços Rest da aplicação:
- /cadastroendereco/gravaEndereco : Grava/Altera os dados de endereço.
- /cadastroendereco/buscaEnderecoByCodEndereco : Consulta os dados de endereço através do código do endereço
- /cadastroendereco/buscaPessoaByCodPessoa : Consulta os dados de Pessoa, através do código da pessoa
- /cadastroendereco/removeEndereco : Remove os dados de endereço através do código do mesmo
- /cadastroendereco/removePessoa : Remove os dados de Pessoa e todos os endereços atrelados a ele.

## /cadastroendereco/gravaEndereco

Serviço no formato de GET, onde deve-se alterar/gravar os dados de endereço do cliente, onde a regra é:
- Caso os ID de pessoa Nulo:
  - Busca pelo nome da pessoa
    - Se encontrar grava o endereço abaixo dessa pessoa
    - Se não encontrar, grava uma pessoa nova e grava os endereços para essa nova pessoa. 
- Caso ID da pessoa preenchido:
  - Busca pelos dados da pessoa 
    - Se encontrar pars ao ID, grava o endereço abaixo dela
    - Senão encontrar lança uma exception
- Caso ID de endereço preenchido
  - Busca pelo ID do endereço e ID da pessoa
  - Se encontrar o endereço, atualiza o mesmo
  - Senão encontrar lança exception
- Caso ID do endereço Nulo
  - Grava um novo endereço.

Para a gravação do endereço é chamado o serviço de busca de CEP caso o mesmo apresente algum erro, o mesmo é lançado para o usuário.
  

- JSON Request
O request para o serviço é;

{
  "pessoaEnderecosTO": [
    {
      "numCep": "string",
      "numLogradouro": "string",
      "sglUf": "string",
      "dscComplemento": "string",
      "nomCidade": "string",
      "nomBairro": "string",
      "codPessoaEndereco": 0,
      "nomLogradouro": "string"
    }
  ],
  "codPessoa": 0,
  "nomPessoa": "string"
}

Campos:
- pessoaEnderecosTO - Lista contendo todos os endereços para cadastro
  - numCep : CEP do endereço
  - numLogradouro : Número do endereço
  - sglUf : Uf do endereço
  - dscComplemento : Complemento do endereço
  - nomCidade : Cidade do endereço
  - nomBairro : Nome do bairro do endereço
  - codPessoaEndereco : Código do endereço para alteração, para pesquisa, exclusao ou alteração, posterios
  - nomLogradouro : Logradouro do endereço
- codPessoa : Código da pessoa para alteração
- nomPessoa : Nome da pessoa

- JSON Response
O Response caso de sucesso retorna os dados do cadastro, endereço com os seus respectivos IDs.
{
  "pessoaEnderecosTO": [
    {
      "numCep": "string",
      "numLogradouro": "string",
      "sglUf": "string",
      "dscComplemento": "string",
      "nomCidade": "string",
      "nomBairro": "string",
      "codPessoaEndereco": 0,
      "nomLogradouro": "string"
    }
  ],
  "codPessoa": 0,
  "nomPessoa": "string"
}

Campos:
- pessoaEnderecosTO - Lista contendo todos os endereços para cadastro
  - numCep : CEP do endereço
  - numLogradouro : Número do endereço
  - sglUf : Uf do endereço
  - dscComplemento : Complemento do endereço
  - nomCidade : Cidade do endereço
  - nomBairro : Nome do bairro do endereço
  - codPessoaEndereco : Código do endereço, para pesquisa, exclusao ou alteração, posterios
  - nomLogradouro : Logradouro do endereço
- codPessoa : Código da pessoa
- nomPessoa : Nome da pessoa


## /cadastroendereco/buscaEnderecoByCodEndereco

Serviço no formato de POST a busca pelos dados do endereço através do código do endereço.

- JSON Request
O Request, é no formato REST com os seguintes campos:
- codPessoaEndereco : Código do endereço

- JSON Response

O response será:
{
  "numCep": "string",
  "numLogradouro": "string",
  "sglUf": "string",
  "dscComplemento": "string",
  "nomCidade": "string",
  "nomBairro": "string",
  "codPessoaEndereco": 0,
  "nomLogradouro": "string"
}

Campos em caso de sucesso:

- numCep : CEP do endereço
- numLogradouro : Número do endereço
- sglUf : Uf do endereço
- dscComplemento : Complemento do endereço
- nomCidade : Cidade do endereço
- nomBairro : Nome do bairro do endereço
- codPessoaEndereco : Código do endereço, para pesquisa, exclusao ou alteração, posterios
- nomLogradouro : Logradouro do endereço


## /cadastroendereco/buscaPessoaByCodPessoa

Serviço no formato de POST a busca pelos dados da pessoa através do código da pessoa.

- JSON Request
O Request, é no formato REST com os seguintes campos:
- codPessoa : Código da pessoa

- JSON Response

O Response caso de sucesso retorna os dados do cadastro, endereço com os seus respectivos IDs.
{
  "pessoaEnderecosTO": [
    {
      "numCep": "string",
      "numLogradouro": "string",
      "sglUf": "string",
      "dscComplemento": "string",
      "nomCidade": "string",
      "nomBairro": "string",
      "codPessoaEndereco": 0,
      "nomLogradouro": "string"
    }
  ],
  "codPessoa": 0,
  "nomPessoa": "string"
}

Campos:
- pessoaEnderecosTO - Lista contendo todos os endereços para cadastro
  - numCep : CEP do endereço
  - numLogradouro : Número do endereço
  - sglUf : Uf do endereço
  - dscComplemento : Complemento do endereço
  - nomCidade : Cidade do endereço
  - nomBairro : Nome do bairro do endereço
  - codPessoaEndereco : Código do endereço, para pesquisa, exclusao ou alteração, posterios
  - nomLogradouro : Logradouro do endereço
- codPessoa : Código da pessoa
- nomPessoa : Nome da pessoa

##  /cadastroendereco/removeEndereco

Serviço no formato de POST que efetua a exclusão dos dados de endereço.

- JSON Request
O Request, é no formato REST com os seguintes campos:
- codPessoaEndereco : Código do endereço

- JSON Response

O response será:
- 200	- Remoção efetuado com sucesso
- 431	- Nenhum endereço encontrada

##  /cadastroendereco/removePessoa

Serviço no formato de POST que efetua a exclusão dos dados da pessoa, bem como todos os endereços vinculados a ela.

- JSON Request
O Request, é no formato REST com os seguintes campos:
- codPessoa : Código da pessoa

- JSON Response

O response será:
- 200	- Remoção efetuado com sucesso
- 430	- Nenhuma pessoa encontrada
