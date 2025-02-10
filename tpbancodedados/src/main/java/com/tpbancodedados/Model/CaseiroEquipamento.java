package com.tpbancodedados.Model;

public class CaseiroEquipamento {
    private int idCaseiro;
    private int idEquipamento;

    public CaseiroEquipamento() {}

    public CaseiroEquipamento(int idCaseiro, int idEquipamento) {
        this.idCaseiro = idCaseiro;
        this.idEquipamento = idEquipamento;
    }

    public int getIdCaseiro() {
        return idCaseiro;
    }

    public void setIdCaseiro(int idCaseiro) {
        this.idCaseiro = idCaseiro;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    @Override
    public String toString() {
        return "CaseiroEquipamento{" +
                "idCaseiro=" + idCaseiro +
                ", idEquipamento=" + idEquipamento +
                '}';
    }
}
