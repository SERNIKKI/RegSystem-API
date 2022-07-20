package com.bs.regsystemapi.modal.vo.patient;

import lombok.Data;

import java.io.Serializable;

@Data
public class PatientInfo implements Serializable {

    private String patNickName;
    private String patImgsrc;
    private String patGender;
    private String patRealName;
    private String patPhone;
    private String patAddress;
    private String patMail;


}
