package br.com.pedra_papel_tesoura.service;

import br.com.pedra_papel_tesoura.model.Mao;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface JogoService {
    
    ResponseEntity<Map<String, Object>> jogar(Mao jogador1, Mao jogador2);
}
