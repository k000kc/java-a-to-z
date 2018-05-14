package ru.apetrov.entity;

import java.util.Objects;

public class User {

    private Long id;
    private String firstName;
    private String seconfName;
    private Long addressId;
    private Long roleId;
    private Long musicTypeId;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
        User user = (User) o;
        return Objects.equals(id, user.id);
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
