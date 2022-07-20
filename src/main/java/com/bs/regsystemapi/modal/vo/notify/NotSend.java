package com.bs.regsystemapi.modal.vo.notify;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/29 21:26
 */
@Data
public class NotSend implements Serializable {

    private String notifyObject;

    private String systemNo;

    private String userRealName;

}