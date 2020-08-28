
# buscaCep
Um simples exemplo de API rest para consulta de endereço a partir do cep. Por **Sâmara Santino** 


## Motivação
Este projetó é requisito mandatório em um processo seletivo, e, adicionalmente, uma boa oportunidade para contribuir dividindo um pouco de conhecimento - e código.


## Escopo
* Expor um serviço de BUSCA DE CEP
* Informar um CEP e obter o nome da RUA, BAIRRO, CIDADE e ESTADO.
Os critérios de aceite são:
        · Dado um CEP válido, deve retornar o endereço correspondente;
        · Dado um CEP válido, que não exista o endereço, deve substituir um dígito da direita para a esquerda por zero até que o endereço seja localizado   (Exemplo: Dado 22333999 tentar com 22333990, 22333900 …);
        · Dado um CEP inválido, deve retornar uma mensagem reportando o erro: "CEP inválido";
        · Os serviços devem receber e responder JSON;

## Pré-requisitos 

### Conhecimento
Pra entender o projeto é bom saber:
* Orientação a objetos com java
* Maven

E um pouco de:
* HTTP
* Rest / RESTful
* Injeção de Dependências

### Ambiente
Para rodar o projeto você vai precisar de:
* [JDK 8+](https://www.oracle.com/sa/java/) - Java 
* [Eclipse](https://www.jboss.org/) - IDE utilizada para o desenvolvimento
* [Jboss](https://www.oracle.com/sa/java/) - Servidor de aplicações
* [Maven](https://maven.apache.org/) - Gerenciamento de Dependências
* [Lombok](https://projectlombok.org/) - Pra diminuir um pouco a verbosidade. É uma biblioteca, mas precisa executar o jar ou instalar o plugin para que funcione ok.


## Arquitetura
A estrutura do código tem base no modelo em camadas, excluindo-se a camada de dados, a qual optei por não implementar.
A interface de comunicação com o sistema (recursos) configura a camada de visão, enquanto a responsabilidade das regras está nas classes de serviço, configurando a controller, e as classes envolvidas na comunicação são um tipo de modelo;
Os recursos utilizados para estruturar a foram:
* EJB para injeção de dependências e gestão de status de sessão dos beans
* JAX-WS para a interface de comunicação
* JUnit para os testes unitários


## Linguagem
Quando se fala Java em meio a tanta tecnologia nova, menos verbosa, e com ambiente muito mais fácil de configurar, surge a pergunta: mas por quê java? Bom, de forma simples: porque paga os boletos. :D
Agora, falando sério, Java é uma linguagem que tem mais de 20 anos, "sobreviveu" a muitas mudanças. É escalável, é confiável, é de graça, e, mesmo em 2020, é uma das linguagens portáveis com maior quantidade e qualidade em frameworks que nos permite fazer de um tudo, de aplicações pequenas a ecossistemas complexos. Tudo depende da necessidade.

**Neste projeto eu não optei por utilizar java dado seus inúmeros atributos, mas sim porque é de longe a linguagem com a qual mais trabalho, então estou familiarizada com o paradigma e abstração, além de bibliotecas e recursos da IDE, o que me deixou mais confiante para utilizá-la.**

## Estratégia de Desenvolvimento
Ao compreender o escopo da API e os requisitos mandatórios, comecei elencando as atividades em 4 categorias:
1. Estrutura - Atividades relacionadas com a arquitetura, ambiente e configurações do git;
2. Codificação - Efetivamente a construçao das funcionalidades;
3. Documentação - Testes, este README e a documentação da API no Swagger;
4. Extra - Itens elencados como "extras" na definição do desafio;

Utilizei o [trello](https://trello.com/b/P3aMQZ0q/buscacep) para fazer a gestão e organização das atividades. Ao longo do desenvolvimento acabei fazendo muitas buscas, então utilizei também para organizar os links de referência junto às tarefas correlatas.


## Baixando o projeto

What things you need to install the software and how to install them

```
Give examples
```

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details





