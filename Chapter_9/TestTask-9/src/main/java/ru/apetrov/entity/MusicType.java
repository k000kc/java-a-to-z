package ru.apetrov.entity;

import java.util.Objects;

public class MusicType {

    private long id;
    private String type;

    public MusicType() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicType musicType = (MusicType) o;
        return id == musicType.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MusicType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
