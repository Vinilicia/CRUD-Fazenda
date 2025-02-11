package com.tpbancodedados.model;

public class Plantacao {
    private int idPlantacao;
    private int idAgronomo;
    private String tipo;
    private float area;
    private String dataPlantio;
    private String dataColheita;

    // Getters e Setters
    public int getIdPlantacao() {
        return idPlantacao;
    }

    public void setIdPlantacao(int idPlantacao) {
        this.idPlantacao = idPlantacao;
    }

    public int getIdAgronomo() {
        return idAgronomo;
    }

    public void setIdAgronomo(int idAgronomo) {
        this.idAgronomo = idAgronomo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getDataPlantio() {
        return dataPlantio;
    }

    public void setDataPlantio(String dataPlantio) {
        this.dataPlantio = dataPlantio;
    }

    public String getDataColheita() {
        return dataColheita;
    }

    public void setDataColheita(String dataColheita) {
        this.dataColheita = dataColheita;
    }
}
