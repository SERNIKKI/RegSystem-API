package com.bs.regsystemapi.modal.vo.user;

import com.bs.regsystemapi.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/4/20 15:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends UserEntity implements Serializable {

    private String department;

}
