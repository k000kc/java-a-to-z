package ru.apetrov.models;

public class Transmission {

    private int id;
    private String typeTransmission;

    public Transmission() {
    }

    public Transmission(int id, String typeTransmission) {
        this.id = id;
        this.typeTransmission = typeTransmission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeTransmission() {
        return typeTransmission;
    }

    public void setTypeTransmission(String typeTransmission) {
        this.typeTransmission = typeTransmission;
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "id=" + id +
                ", typeTransmission='" + typeTransmission + '\'' +
                '}';
    }
}
