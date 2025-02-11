package com.tpbancodedados.model;

public enum EstadoEquipamento {
    DISPONIVEL("Disponível"),
    QUEBRADO("Quebrado"),
    EM_MANUTENCAO("Em manutenção");

    private final String descricao;

    EstadoEquipamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
