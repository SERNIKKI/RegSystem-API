package com.bs.regsystemapi.modal.dto.user;

import lombok.Data;

/**
 * @Author qpj
 * @Date: 2022/02/22/ 9:44
 * @Description
 */
@Data
public class FindPasswordForm {
    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 验证码
     */
    private String code;
}
