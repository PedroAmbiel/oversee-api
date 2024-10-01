package com.oversee.dto;

import com.oversee.model.Address;

import java.io.Serializable;

public class ClientResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Address address;


    public ClientResponseDTO() {

    }
}
