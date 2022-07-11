
# Shrtner

Dada uma URL, o Shrtner deve gerar um pseudônimo da mesma. O qual chamamos de "Short Link".

Como o nome ja diz, esta aplicação se trata de um encurtador de links. Onde tem como único e principal objetivo, transformar links e deixa-los de uma forma melhor apresentável e de fácil leitura, cumprindo assim seu papel de redirecionar para o link original ao serem referenciados em outras aplicações.

![interface](https://github.com/irwing-moura/shrtner/blob/main/documenta%C3%A7%C3%A3o/main_page.png?raw=true)


## Requisitos

Para construir e executar o aplicativo, você precisa:

- [JDK 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Rodando localmente


Existem várias maneiras de executar um aplicativo Spring Boot em sua máquina local. Uma maneira é executar o método `main` na classe ` irwing.moura.shrtner.ShrtnerApplication` da sua IDE.

Outra forma é usar o  [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) da forma a seguir:

```shell
mvn spring-boot:run
```


## Rest API's

### Criando uma URL encurtada

#### Requisição

`POST /api/generate`

    http://localhost:8080/api/generate

#### Resposta

	"originalUrl": "https://github.com/irwing-moura/shrtner",
	"shortLink": "f043df2b",
	"expirationDate": "2022-04-21T23:45:33.2876261"


### Redirecionando uma URL encurtada

#### Requisição

`POST /api/{shortLink}`

    http://localhost:8080/api/{shortLink}

#### Resposta

  
	HTTP/1.1 302 - Redirect
## Demonstração de uso com interface

![Alt Text](https://media.giphy.com/media/NHrc2P0XXzUTxM46MW/giphy.gif)
## Autor

- [Irwing Moura](https://github.com/irwing-moura)

