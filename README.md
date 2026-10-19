# Pedra, papel e tesoura

Aplicação REST em Java com Spring Boot que demonstra uma partida de pedra, papel e tesoura. Os gestos são mantidos em uma fonte em memória, e a regra do jogo é avaliada por um serviço.

## Tecnologias

- Java 27 (versão configurada no `pom.xml`)
- Spring Boot 4.1.1
- Maven
- Lombok

## Como executar

Com Java 27 instalado, execute na raiz do projeto:

```bash
./mvnw spring-boot:run
```

No Windows, use `mvnw.cmd spring-boot:run`.

A aplicação inicia na porta padrão do Spring Boot, `8080`.

## Endpoint

```http
GET /api/jogo
```

Exemplo local:

```bash
curl http://localhost:8080/api/jogo
```

O endpoint executa uma partida fixa: jogador 1 escolhe `PEDRA` e jogador 2 escolhe `PAPEL`. A resposta é um JSON semelhante a:

```json
{
  "jogador1": { "gesto": "PEDRA" },
  "jogador2": { "gesto": "PAPEL" },
  "resultado": "PERDE",
  "mensagem": "PAPEL vence PEDRA, jogador 2 vence!"
}
```

## Organização do código

- `controller/JogarController`: expõe `GET /api/jogo`, busca os gestos disponíveis e monta as mãos dos dois jogadores.
- `repository/GestosRepository`: fornece os gestos `PEDRA`, `PAPEL` e `TESOURA` em memória, simulando uma fonte externa.
- `model/Gesto`: representa um gesto e as listas de gestos que ele vence e para os quais perde.
- `model/Mao`: representa a mão de um jogador, contendo seu gesto.
- `service/JogoService` e `service/impl/JogoImpl`: definem e implementam a avaliação da partida e a montagem da resposta.
- `model/enums/Resultado`: enumera os resultados `GANHA`, `PERDE` e `EMPATA` para o jogador 1.

O Spring gerencia o controller, o serviço e a fonte de gestos como beans, conectando-os por injeção de dependência.

## Escopo atual

Esta implementação cobre o fluxo básico da partida, mas o endpoint ainda usa escolhas fixas: não recebe gestos por parâmetros ou corpo da requisição. Os gestos também são dados em memória, não persistidos nem obtidos de uma API real. O teste existente verifica que o contexto Spring inicia; não há ainda testes específicos para as regras do jogo ou para o endpoint.
