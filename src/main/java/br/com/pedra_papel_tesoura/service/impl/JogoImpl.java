package br.com.pedra_papel_tesoura.service.impl;

import br.com.pedra_papel_tesoura.model.Mao;
import br.com.pedra_papel_tesoura.model.enums.Resultado;
import br.com.pedra_papel_tesoura.service.JogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JogoImpl implements JogoService {

    @Override
    public ResponseEntity<Map<String, Object>> jogar(Mao jogador1, Mao jogador2) {
        Resultado resultado;
        if (jogador1.gesto.vitorias().stream()
                .anyMatch(g -> g.gesto().equals(jogador2.gesto.gesto()))) {
            resultado = Resultado.GANHA;
        } else if (jogador1.gesto.derrotas()
                .stream()
                .anyMatch(g -> g.gesto().equals(jogador2.gesto.gesto()))) {
            resultado = Resultado.PERDE;
        } else {
            resultado = Resultado.EMPATA;
        }

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("jogador1", Map.of("gesto", jogador1.gesto.gesto()));
        resposta.put("jogador2", Map.of("gesto", jogador2.gesto.gesto()));
        resposta.put("resultado", resultado.name());
        resposta.put("mensagem", gerarMensagem(jogador1.gesto.gesto(), jogador2.gesto.gesto(), resultado));

        return ResponseEntity.ok(resposta);
    }

    private String gerarMensagem(String gesto1, String gesto2, Resultado resultado) {
        return switch (resultado) {
            case GANHA -> gesto1 + " vence " + gesto2 + ", jogador 1 vence!";
            case PERDE -> gesto2 + " vence " + gesto1 + ", jogador 2 vence!";
            case EMPATA -> "Ambos jogaram igual, empate!";
        };
    }
}
