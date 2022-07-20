package com.bs.regsystemapi.modal.dto.department;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/14 16:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDepartmentForm extends ManagePageResult implements Serializable {

    private String secondDepartment;

    private String department;
}
