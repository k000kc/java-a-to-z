package ru.apetrov.entity;

import java.util.Objects;

public class UserIdMusicTypeId {

    private Long userId;
    private Long musicTypeId;

    public UserIdMusicTypeId() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMusicTypeId() {
        return musicTypeId;
    }

    public void setMusicTypeId(Long musicTypeId) {
        this.musicTypeId = musicTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIdMusicTypeId that = (UserIdMusicTypeId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(musicTypeId, that.musicTypeId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, musicTypeId);
    }

    @Override
    public String toString() {
        return "UserIdMusicTypeId{" +
                "userId=" + userId +
                ", musicTypeId=" + musicTypeId +
                '}';
    }
}

