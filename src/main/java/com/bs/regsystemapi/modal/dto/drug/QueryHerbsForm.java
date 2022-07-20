package com.bs.regsystemapi.modal.dto.drug;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/17 15:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryHerbsForm extends ManagePageResult implements Serializable {

    private String herbName;

    private String herbPinyin;

    private String herbAction;

    private String herbOrigin;

    private String herbPlace;
}
