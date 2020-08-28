
# buscaCep
Um simples exemplo de API rest para consulta de endereço a partir do cep. Por **Sâmara Santino** 


## Motivação
Este projetó é requisito mandatório em um processo seletivo, e, adicionalmente, uma boa oportunidade para contribuir dividindo um pouco de conhecimento - e código.


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
* HTTP
* Rest / RESTful
* Injeção de Dependências

### Ambiente
Para rodar o projeto você vai precisar de:
* [JDK 8+](https://www.oracle.com/sa/java/) - Java 
* [Eclipse](https://www.eclipse.org/) - IDE utilizada para o desenvolvimento
* [Wildfly](https://www.wildfly.org/) - Servidor de aplicações, ou outro de sua preferência
* [Maven](https://maven.apache.org/) - Gerenciamento de Dependências
* [Lombok](https://projectlombok.org/) - Pra o código ficar menos verboso. É uma biblioteca, mas precisa executar o jar ou instalar o plugin para que funcione ok.


## Arquitetura
A estrutura do código tem base no modelo em camadas, de forma simplificada.
A interface de comunicação com o sistema (recursos do webservice) configura a camada de visão;
A responsabilidade das negócio está nas classes de serviço, configurando os controladores;
As classes pojo envolvidas na comunicação são um tipo de modelo;
enquanto uma classe com contexto de aplicação faz as vezes de contêiner de dados. Optei por não utilizar integração com banco de dados para diminuir a complexidade

Os recursos utilizados para escrever o código foram:
* EJB para injeção de dependências e gestão de status de sessão dos beans
* JAX-WS para a interface de comunicação
* Jackson como JSON parser
* JUnit para os testes unitários


## Linguagem
Java é uma linguagem que tem mais de 20 anos, "sobreviveu" a muitas mudanças. É escalável, é confiável, é de graça, e, ainda hoje, é uma das linguagens portáveis com maior quantidade e qualidade em frameworks que nos permite fazer de tudo, de aplicações pequenas a ecossistemas complexos. Tudo depende da necessidade. No entanto, neste projeto eu não optei por utilizar java dados seus atributos, mas sim por uma necessidade pessoal. É a linguagem na qual tenho maior conhecimento, tanto do paradigma e abstração, quanto das bibliotecas e recursos da IDE, o que me deixou mais confiante para utilizá-la.


## Estratégia de Desenvolvimento
Ao compreender o escopo da API e os requisitos mandatórios, comecei elencando as atividades em 4 categorias:
1. Estrutura - Atividades relacionadas com a arquitetura, ambiente e configurações do git;
2. Codificação - Efetivamente a construçao das funcionalidades;
3. Documentação - Testes, este README e a documentação da API no Swagger;
4. Extra - Itens elencados como "extras" na definição do desafio;

Utilizei o [trello](https://trello.com/b/P3aMQZ0q/buscacep) para fazer a gestão e organização das atividades. Ao longo do desenvolvimento acabei fazendo muitas buscas, então utilizei também para organizar os links de referência junto às tarefas correlatas.

Meu primeiro objetivo foi produzir o mínimo produto viável em termos de código e documentação para a entrega do projeto, e depois trabalhar nos itens desejáveis, deixando os extras por último.

## Baixando o projeto

What things you need to install the software and how to install them

```
Give examples
```

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details





