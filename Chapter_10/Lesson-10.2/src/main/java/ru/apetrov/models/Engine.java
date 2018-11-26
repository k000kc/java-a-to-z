package ru.apetrov.models;

public class Engine {

    private int id;
    private int enginePower;
    private double engineCapacity;

    public Engine() {
    }

    public Engine(int id, int enginePower, double engineCapacity) {
        this.id = id;
        this.enginePower = enginePower;
        this.engineCapacity = engineCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", enginePower=" + enginePower +
                ", engineCapacity=" + engineCapacity +
                '}';
    }
}
