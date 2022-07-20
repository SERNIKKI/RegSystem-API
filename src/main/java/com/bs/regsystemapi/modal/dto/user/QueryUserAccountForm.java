package com.bs.regsystemapi.modal.dto.user;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryUserAccountForm extends ManagePageResult implements Serializable {

    private String userRealName;

    private String userName;

    private String loginState;

    private String loginOnline;

    private String showNotLogin;

}
