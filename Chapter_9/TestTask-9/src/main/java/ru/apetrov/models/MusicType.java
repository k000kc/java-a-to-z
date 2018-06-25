package ru.apetrov.models;

import java.util.Objects;

public class MusicType {

    private Integer id;
    private String musicType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicType musicType = (MusicType) o;
        return Objects.equals(id, musicType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MusicType{" +
                "id=" + id +
                ", musicType='" + musicType + '\'' +
                '}';
    }
}
