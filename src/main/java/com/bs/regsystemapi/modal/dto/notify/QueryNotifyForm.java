package com.bs.regsystemapi.modal.dto.notify;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/29 15:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryNotifyForm extends ManagePageResult implements Serializable {

    private String notifyTitle;

    private String notifyType;

}
