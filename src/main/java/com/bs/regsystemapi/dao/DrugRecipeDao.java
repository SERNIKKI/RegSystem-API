package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.DrugRecipe;
import com.bs.regsystemapi.modal.dto.drug.QueryRecipeForm;
import com.bs.regsystemapi.modal.vo.drug.RecipeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/17 10:49
 */
@Mapper
public interface DrugRecipeDao extends BaseMapper<DrugRecipe> {
    List<RecipeInfo> getRecipeList(QueryRecipeForm form);
    RecipeInfo getRecipeInfo(String recipeNo);
    void deleteRecipe(DrugRecipe drugRecipe);
    void recoverRecipe(DrugRecipe drugRecipe);
    List<RecipeInfo> getDeleteList();
}
