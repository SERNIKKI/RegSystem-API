package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.DrugRecipe;
import com.bs.regsystemapi.modal.dto.drug.QueryRecipeForm;
import com.bs.regsystemapi.modal.vo.drug.RecipeInfo;
import com.bs.regsystemapi.utils.ManagePageResult;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/17 10:57
 */
public interface DrugRecipeService extends IService<DrugRecipe> {
    ManagePageResult getRecipeList(QueryRecipeForm form);
    RecipeInfo getRecipeInfo(String recipeNo);
    int updateRecipeInfo(DrugRecipe drugRecipe);
    void deleteRecipe(DrugRecipe drugRecipe);
    void recoverRecipe(DrugRecipe drugRecipe);
    List<RecipeInfo> getDeleteList();
}
