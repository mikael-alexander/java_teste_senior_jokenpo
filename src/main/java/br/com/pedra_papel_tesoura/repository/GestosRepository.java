package br.com.pedra_papel_tesoura.repository;

import br.com.pedra_papel_tesoura.model.Gesto;
import org.springframework.stereotype.Component;

import java.util.List;

/** Fonte fake dos gestos, simulando uma API externa. */
@Component
public class GestosRepository {

    public List<Gesto> buscarGestos() {
        Gesto pedraBase = new Gesto("PEDRA", List.of(), List.of());
        Gesto papelBase = new Gesto("PAPEL", List.of(), List.of());
        Gesto tesouraBase = new Gesto("TESOURA", List.of(), List.of());

        Gesto pedra = new Gesto("PEDRA", List.of(tesouraBase), List.of(papelBase));
        Gesto papel = new Gesto("PAPEL", List.of(pedraBase), List.of(tesouraBase));
        Gesto tesoura = new Gesto("TESOURA", List.of(papelBase), List.of(pedraBase));

        return List.of(pedra, papel, tesoura);
    }
}
