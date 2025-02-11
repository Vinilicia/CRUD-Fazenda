package com.tpbancodedados.model;

public class Equipamento {
    private int idEquipamento;
    private String descricao;
    private EstadoEquipamento estado;

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EstadoEquipamento getEstado() {
        return estado;
    }

    public void setEstado(EstadoEquipamento estado) {
        this.estado = estado;
    }
}
