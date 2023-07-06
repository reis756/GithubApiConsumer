# Github Api Consumer

Um app Android para consumir dados de usuários na API do Github. Nele é possível pesquisar um usuário específico do Github, listar os usuários e visualizar seus respectivos repositórios

![](https://github.com/reis756/GithubApiConsumer/blob/main/app.gif)

## Passos para implementação

* Primeiro, clone o projeto `git clone https://github.com/reis756/GithubApiConsumer.git`
* Acesse [`Fine-grained personal access tokens`](https://github.com/settings/tokens?type=beta) 
* Crie um Fine-grained personal access token clicando em `Generate new token`. 
* Preencha os campos solicitados
* IMPORTANTE: não esqueça de copiar o token gerado. Caso contrário, será necessário criar novamente
* Cole o token gerado no arquivo `local.properties` seguindo o padrão abaixo:

```
GITHUB_TOKEN=token_gerado
```

## Arquitetura

Arquitetura pensada visando a facilidade na manutenção futura e escalabilidade. App modularizado, evitando a hiper dependência das camadas do aplicativo. Seguindo o padrão Clean Architecture + MVVM, recomendado por [`Developer Android`](https://developer.android.com/jetpack/guide?hl=pt-br). Segue abaixo o esquema de pastas:

![`Esquema de pastas`](https://github.com/reis756/GithubApiConsumer/blob/main/app_scheme.png)

## Bibliotecas e APIs

* ***Linguagem Kotlin***
* ***Material Design 3***
* ***Koin*** - para injeção de dependência
* ***Retrofit 2*** - para requisições a API 
* ***Paging 3*** - para paginação de resultados
* ***Glide*** - para carregamento de imagens
* ***Proguard*** - para ofuscação de código
* ***JUnit*** - para testes unitários
* ***Turbine*** - para testes em flows
* ***Expresso*** - para testes instrumentados

## ToDo's

* Aumentar a cobertura de testes unitários e testes instrumentados
* Implementar lint
* Implementar detekt
* Implementar Firebase (Crashlytics, Analytics, Big Query)
* Implementar CI/CD com Fastlane, Github Actions

