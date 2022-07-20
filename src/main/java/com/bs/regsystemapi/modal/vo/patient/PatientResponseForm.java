package com.bs.regsystemapi.modal.vo.patient;

import lombok.Data;

/**
 * @Date 2022/4/27 19:48
 */
@Data
public class PatientResponseForm {

    private String patNickName;
    private String patImgsrc;
    private String patGender;
    private String patRealName;
    private String patPhone;
    private String patAddress;
    private String patMail;

}
