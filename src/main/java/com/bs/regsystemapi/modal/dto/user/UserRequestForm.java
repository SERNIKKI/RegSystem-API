package com.bs.regsystemapi.modal.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequestForm implements Serializable {

    private String userName;

    private String userPassword;
}
