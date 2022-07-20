package com.bs.regsystemapi.modal.dto.drug;

import com.bs.regsystemapi.utils.ManagePageResult;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/19 17:52
 */
@Data
public class QueryRecipeForm extends ManagePageResult implements Serializable {

    private String recipeName;

    private String recipePinyin;

    private String recipeAction;

    private String recipeIndication;

    private String recipeOrigin;
}
