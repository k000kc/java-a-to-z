package ru.apetrov.entity;

import java.util.Objects;

public class UserIdMusicTypeId {

    private long userId;
    private long musicTypeId;

    public UserIdMusicTypeId() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMusicTypeId() {
        return musicTypeId;
    }

    public void setMusicTypeId(long musicTypeId) {
        this.musicTypeId = musicTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIdMusicTypeId that = (UserIdMusicTypeId) o;
        return userId == that.userId &&
                musicTypeId == that.musicTypeId;
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

