package ru.apetrov.models;

public class Car {

    private int id;
    private String condition;
    private String release;
    private CarBody carBody;
    private Engine engine;
    private Transmission transmission;

    public Car() {
    }

    public Car(int id, String condition, String release, CarBody carBody, Engine engine, Transmission transmission) {
        this.id = id;
        this.condition = condition;
        this.release = release;
        this.carBody = carBody;
        this.engine = engine;
        this.transmission = transmission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", condition='" + condition + '\'' +
                ", release='" + release + '\'' +
                ", carBody=" + carBody +
                ", engine=" + engine +
                ", transmission=" + transmission +
                '}';
    }
}
