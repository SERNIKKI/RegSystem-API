package com.bs.regsystemapi.modal.dto.notify;

import com.bs.regsystemapi.entity.Notify;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/29 18:23
 */
@Data
public class SendNotifyForm implements Serializable {

    private List<String> notifyObjects;

    private Notify notify;

}
