package br.com.pedra_papel_tesoura.controller;

import br.com.pedra_papel_tesoura.model.Gesto;
import br.com.pedra_papel_tesoura.model.Mao;
import br.com.pedra_papel_tesoura.service.JogoService;
import br.com.pedra_papel_tesoura.repository.GestosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class JogarController {

    @Autowired
    private JogoService jogoService;

    @Autowired
    private GestosRepository gestosRepository;

    @GetMapping("/jogo")
    public ResponseEntity<Map<String, Object>> jogarFake() {
        Map<String, Gesto> gestos = gestosRepository.buscarGestos().stream()
                .collect(Collectors.toMap(Gesto::gesto, gesto -> gesto));
        Mao jogador1 = new Mao();
        jogador1.gesto = gestos.get("PEDRA");

        Mao jogador2 = new Mao();
        jogador2.gesto = gestos.get("PAPEL");

        return jogoService.jogar(jogador1, jogador2);
    }
}
