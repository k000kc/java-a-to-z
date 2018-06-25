package ru.apetrov.models;

import java.util.Objects;

public class UserLoginMusicTypeId {

    private String userLogin;
    private Integer musicTypeId;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Integer getMusicTypeId() {
        return musicTypeId;
    }

    public void setMusicTypeId(Integer musicTypeId) {
        this.musicTypeId = musicTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginMusicTypeId that = (UserLoginMusicTypeId) o;
        return Objects.equals(userLogin, that.userLogin) &&
                Objects.equals(musicTypeId, that.musicTypeId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userLogin, musicTypeId);
    }

    @Override
    public String toString() {
        return "UserLoginMusicTypeId{" +
                "userLogin='" + userLogin + '\'' +
                ", musicTypeId=" + musicTypeId +
                '}';
    }
}
