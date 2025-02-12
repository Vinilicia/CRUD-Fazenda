package com.tpbancodedados.model;

import java.time.LocalDate;

public class Plantacao {
    private int idPlantacao;
    private int idAgronomo;
    private String cultura;
    private float area;
    private LocalDate dataPlantio;
    private LocalDate dataColheita;

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

    public String getCultura() {
        return cultura;
    }

    public void setCultura(String cultura) {
        this.cultura = cultura;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public LocalDate getDataPlantio() {
        return dataPlantio;
    }

    public void setDataPlantio(LocalDate dataPlantio) {
        this.dataPlantio = dataPlantio;
    }

    public LocalDate getDataColheita() {
        return dataColheita;
    }

    public void setDataColheita(LocalDate dataColheita) {
        this.dataColheita = dataColheita;
    }

	@Override
	public String toString() {
		return "ID = " + idPlantacao + 
			", ID Agronomo = " + idAgronomo + 
			", Cultura = " + cultura + 
			", Area = " + area + 
			", Data Plantio = " + dataPlantio + 
			", Data Colheita = " + dataColheita;
	}

}
