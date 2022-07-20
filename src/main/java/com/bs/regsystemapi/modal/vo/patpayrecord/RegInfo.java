package com.bs.regsystemapi.modal.vo.patpayrecord;

import com.bs.regsystemapi.entity.PatRegRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/5/7 18:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RegInfo extends PatRegRecord implements Serializable {

    private String userRealName;

    private String userNo;

    private String department;

    private String secondDepartment;

    private String personName;

    private String personNo;

    private String patNickName;

    private String patNo;

}
