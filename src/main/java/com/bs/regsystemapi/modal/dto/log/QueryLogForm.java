package com.bs.regsystemapi.modal.dto.log;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/25 10:36
 */
@Data
public class QueryLogForm extends ManagePageResult implements Serializable {

    private String createName;

    private String status;

}
