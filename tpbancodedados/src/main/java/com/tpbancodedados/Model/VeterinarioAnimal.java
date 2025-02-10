package com.tpbancodedados.Model;

public class VeterinarioAnimal {
    private int idVeterinario;
    private int idAnimal;

    public VeterinarioAnimal() {}

    public VeterinarioAnimal(int idVeterinario, int idAnimal) {
        this.idVeterinario = idVeterinario;
        this.idAnimal = idAnimal;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    @Override
    public String toString() {
        return "VeterinarioAnimal{" +
                "idVeterinario=" + idVeterinario +
                ", idAnimal=" + idAnimal +
                '}';
    }
}
