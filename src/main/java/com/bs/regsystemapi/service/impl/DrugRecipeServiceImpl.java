package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.DrugRecipeDao;
import com.bs.regsystemapi.entity.DrugRecipe;
import com.bs.regsystemapi.modal.dto.drug.QueryRecipeForm;
import com.bs.regsystemapi.modal.vo.drug.RecipeInfo;
import com.bs.regsystemapi.service.DrugRecipeService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/17 10:57
 */
@Service("drugRecipeService")
public class DrugRecipeServiceImpl extends ServiceImpl<DrugRecipeDao, DrugRecipe> implements DrugRecipeService {
    @Override
    public ManagePageResult getRecipeList(QueryRecipeForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<RecipeInfo> recipeList = this.baseMapper.getRecipeList(form);
        PageInfo<RecipeInfo> recipeInfoPageInfo = new PageInfo<>(recipeList);
        return ManagePageResult.getPageResult(recipeInfoPageInfo);
    }

    @Override
    public RecipeInfo getRecipeInfo(String recipeNo) {
        RecipeInfo recipeInfo = this.baseMapper.getRecipeInfo(recipeNo);
        return recipeInfo;
    }

    @Override
    public int updateRecipeInfo(DrugRecipe drugRecipe) {
        int i = this.baseMapper.updateById(drugRecipe);
        return i;
    }

    @Override
    public void deleteRecipe(DrugRecipe drugRecipe) {
        this.baseMapper.deleteRecipe(drugRecipe);
    }

    @Override
    public void recoverRecipe(DrugRecipe drugRecipe) {
        this.baseMapper.recoverRecipe(drugRecipe);
    }

    @Override
    public List<RecipeInfo> getDeleteList() {
        List<RecipeInfo> deleteList = this.baseMapper.getDeleteList();
        return deleteList;
    }
}
