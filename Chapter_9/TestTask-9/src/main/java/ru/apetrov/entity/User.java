package ru.apetrov.entity;

import java.util.Objects;

public class User {

    private long id;
    private String firstName;
    private String seconfName;
    private long addressId;
    private long roleId;
    private long musicTypeId;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSeconfName() {
        return seconfName;
    }

    public void setSeconfName(String seconfName) {
        this.seconfName = seconfName;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
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
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", seconfName='" + seconfName + '\'' +
                ", addressId=" + addressId +
                ", roleId=" + roleId +
                ", musicTypeId=" + musicTypeId +
                '}';
    }
}
