package ru.apetrov.models;

public class CarBody {

    private int id;
    private String bodyType;
    private String bodyColor;
    private int numberOfDoors;

    public CarBody() {
    }

    public CarBody(int id, String bodyType, String bodyColor, int numberOfDoors) {
        this.id = id;
        this.bodyType = bodyType;
        this.bodyColor = bodyColor;
        this.numberOfDoors = numberOfDoors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String toString() {
        return "CarBody{" +
                "id=" + id +
                ", bodyType='" + bodyType + '\'' +
                ", bodyColor='" + bodyColor + '\'' +
                ", numberOfDoors=" + numberOfDoors +
                '}';
    }
}
