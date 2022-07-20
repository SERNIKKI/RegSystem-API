package com.bs.regsystemapi.modal.dto;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryTestRequestForm extends ManagePageResult {

    private String testRemark;

    private String createName;

}
