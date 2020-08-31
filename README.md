
# buscaCep
Um exemplo simples de API rest para consulta de informações a partir do cep. Por **Sâmara Santino** 


## Motivação
Este projetó é requisito mandatório em um processo seletivo, e, adicionalmente, uma boa oportunidade para contribuir dividindo um pouco de código.


## Escopo
* Expor um serviço de BUSCA DE CEP: informar um CEP e obter o nome da RUA, BAIRRO, CIDADE e ESTADO 

Os critérios de aceite são: 
* Dado um CEP válido, deve retornar o endereço correspondente; 
* Dado um CEP válido, que não exista o endereço, deve substituir um dígito da direita para a esquerda por zero até que o endereço seja localizado (Exemplo: Dado 22333999 tentar com 22333990, 22333900 …); 
* Dado um CEP inválido, deve retornar uma mensagem reportando o erro: "CEP inválido"; 
* Os serviços devem receber e responder JSON; 

## Pré-requisitos 

### Conhecimento Técnico
Pra entender o projeto é bom saber:
* Orientação a objetos com java
* Maven
* Rest / RESTful
* EJB
* JPA

### Ambiente
Para rodar o projeto você vai precisar de:
* [JDK 8+](https://www.oracle.com/sa/java/) - Java 
* [Eclipse](https://www.eclipse.org/) - IDE utilizada para o desenvolvimento
* [Wildfly](https://www.wildfly.org/) - Servidor de aplicações, ou outro de sua preferência
* [Maven](https://maven.apache.org/) - Gerenciamento de Dependências
* [Lombok](https://projectlombok.org/) - Pra o código ficar menos verboso. É uma biblioteca, mas precisa executar o jar ou instalar o plugin para que funcione ok.
* Algum servidor de banco de dados que a JPA dê suporte. Eu utilizei o [PostgreSQL](https://www.postgresql.org/).


## Arquitetura
A decisão de utilizar esta arquitetura foi orgânica, uma vez que já tinha utilizado algo semelhante anteriormente e consegui levantar o ambiente sem dificuldades.
A estrutura do código tem base no modelo em camadas.

<img src="https://i.ibb.co/GPZsnRp/Critical-to-quality-tree-1-1.png title="ilustração das camadas do sistema" alt="ilustração das camadas do sistema">
                                                                                                               
* A interface de comunicação com o sistema são os recursos do webservice;
* A responsabilidade das regras de negócio está nas classes de serviço;
* As entidades mapeiam os dados persistentes;
* A DAO é a camada de comunicação com o banco de dados;

As bibliotecas utilizadas para escrever o código foram:
* JAX-RS e Jackson (JSON parser) para a interface de comunicação
* EJB para gestão de status dos beans
* JPA com Hibernate para a camada de persistência
* JUnit e Mockito para os testes unitários da camada de serviço.


## Linguagem
Neste projeto eu optei em utilizar java por eu ter maior familiaridade com a linguagem, tanto com o paradigma e abstração, quanto com as bibliotecas e recursos da IDE, o que me deixou mais confiante para utilizá-la. Além deste motivo, Java é uma linguagem que tem mais de 20 anos, "sobreviveu" a muitas mudanças. É escalável, confiável, gratuita, e, ainda hoje, é uma das linguagens portáveis com maior quantidade e qualidade em frameworks que nos permite explorar muitas possibilidades, de aplicações pequenas a ecossistemas complexos. Tudo depende da necessidade.


## Estratégia de Desenvolvimento
Ao compreender o escopo da API e os requisitos mandatórios, comecei elencando as atividades em 4 categorias:
1. Estrutura - Atividades relacionadas com a arquitetura, ambiente e configurações do git;
2. Codificação - Efetivamente a construçao das funcionalidades;
3. Documentação - Itens de documentação da aplicação;
4. Extra - Itens elencados como "extras" na definição do desafio;

Utilizei o trello para fazer a gestão e organização das atividades. Ao longo do desenvolvimento acabei fazendo muitas buscas, então utilizei também para organizar os links de referência junto às tarefas correlatas.

Meu primeiro objetivo foi produzir o mínimo produto viável em termos de código e documentação para a entrega do projeto, e depois trabalhar nos itens desejáveis, deixando os extras por último.


## Sobre o Funcionamento da API
A API foi construída para gerar alguns registros padrão no banco assim que o projeto é inicializado. Não foi possível concluir o módulo de autorização / autenticação, **por este motivo as requisições devem ser feitas sempre passando login = admin e senha = admin**. 
Nas requisi

<details><summary><b>/rest/cep/inserir</b></summary>

*@POST* Insere informações de CEP, neste escopo foi criado somente para auxiliar no caso de haver testes controlados, onde um CEP específico pode ser necessário.

Request
```json
{
"login": "admin",
"senha": "admin",
"cep_informacao":{
"cep": "86047250",
"rua":"Avenida Adhemar Pereira de Barros",
"bairro":"Bela Suíça",
"cidade":"Londrina",
"estado":"PR"}
}
```

Response
```json
{
    "codigo_retorno": 1,
    "mensagem_retorno": "Endereço inserido."
}
```

</details>


<details><summary><b>/rest/cep/buscarTodos</b></summary>

*@POST* Busca todas as informações de CEP da base de dados, neste escopo foi criado somente para auxiliar no caso de haver testes controlados, onde uma consulta pode auxiliar a visualização dos dados disponíveis na base em aderência com as regras mandatórias (ex. substituição de caracteres por 0).

Request
```json
{
"login": "admin",
"senha": "admin"
}
```

Response
```json
{
    "codigo_retorno": 1,
    "mensagem_retorno": "Busca realizada.",
    "ceps": [
        {
            "cep": "14403471",
            "rua": "R. Arnulfo de Lima",
            "bairro": "Vila Santa Cruz",
            "cidade": "Franca",
            "estado": "São Paulo"
        },
        {
            "cep": "02047000",
            "rua": "R. Maria Prestes Maia",
            "bairro": "Vila Guilherme",
            "cidade": "São Paulo",
            "estado": "São Paulo"
        }
    ]
}
```

</details>


<details><summary><b>/rest/cep/buscarPorCep</b></summary>

*@POST* Busca as informações referentes ao CEP passado na requisição. Caso não localize, substitui sequencialmente, da direita para a esquerda, os caracteres diferentes de zero, por zero. Caso mesmo assim não localize, retorna mensagem informando que o CEP não foi localizado. Caso localize retorna as informações do CEP e caso haja algum dado não aderente na requisição, retorna uma mensagem informativa.

Request
```json
{
"login": "admin",
"senha": "admin",
"cep": "86050-350"
}
```

Response
```json
{
    "codigo_retorno": 1,
    "mensagem_retorno": "Endereço não localizado. CEP 86050-350"
}
```

Request
```json
{
"login": "admin",
"senha": "admin",
"cep": "14403471"
}
```

Response
```json
{
    "codigo_retorno": 1,
    "mensagem_retorno": "Endereço localizado.",
    "cep": {
        "cep": "14403471",
        "rua": "R. Arnulfo de Lima",
        "bairro": "Vila Santa Cruz",
        "cidade": "Franca",
        "estado": "São Paulo"
    }
}
```

Request
```json
{
"login": "admin",
"senha": "admin",
"cep": "86050350abc"
}
```

Response
```json
{
    "codigo_retorno": 2,
    "mensagem_retorno": "Cep inválido. "
}
```

</details>


## License

Este projeto está licenciado sob a [MIT License](LICENSE.md).





