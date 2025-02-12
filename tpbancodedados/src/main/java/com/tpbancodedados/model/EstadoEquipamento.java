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

    public static EstadoEquipamento fromDescricao(String descricao) {
        for (EstadoEquipamento estado : values()) {
            if (estado.getDescricao().equalsIgnoreCase(descricao)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado não encontrado para a descrição: " + descricao);
    }
}

