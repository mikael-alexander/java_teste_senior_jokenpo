package br.com.pedra_papel_tesoura.model;


import java.util.List;

public record Gesto (String gesto, List<Gesto> vitorias, List<Gesto> derrotas){}
