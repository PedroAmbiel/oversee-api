package com.oversee.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Client implements Cloneable, Serializable {

    private String name;
    private LocalDate birthday;
    private Address address;
    private String phone;
    private String email;


    public Client(String name, LocalDate birthday, Address address, String phone, String email) {
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Client() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Client clone() {
        try {
            return (Client) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
