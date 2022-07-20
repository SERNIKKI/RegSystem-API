package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.DrugProd;
import com.bs.regsystemapi.entity.DrugRecipe;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.drug.QueryRecipeForm;
import com.bs.regsystemapi.modal.vo.drug.RecipeInfo;
import com.bs.regsystemapi.service.DrugRecipeService;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.NoGeneratorUtil;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/17 10:59
 */
@RestController
@RequestMapping("/drugRecipe")
@Api(value = "中药->方剂接口")
public class DrugRecipeController {

    private static final Logger logger = LoggerFactory.getLogger(DrugRecipeController.class);

    @Autowired
    private DrugRecipeService drugRecipeService;
    @Autowired
    private LogService logService;

    @ApiOperation(value = "获取方剂列表")
    @PostMapping("/list")
    public ResponseResult getRecipeList(@RequestBody QueryRecipeForm form) {
        ManagePageResult recipeList = drugRecipeService.getRecipeList(form);
        return ResponseResult.ok().put(recipeList);
    }

    @ApiOperation(value = "获取方剂详细信息")
    @GetMapping("/info/{recipeNo}")
    public ResponseResult getRecipeInfo(@PathVariable("recipeNo") String recipeNo) {
        if(StringUtils.isEmpty(recipeNo)) {
            return ResponseResult.error("方剂no不能为空！");
        }
        RecipeInfo recipeInfo = drugRecipeService.getRecipeInfo(recipeNo);
        return ResponseResult.ok().put(recipeInfo);
    }

    @ApiOperation(value = "更改方剂信息")
    @PostMapping(value = "/update")
    public ResponseResult updateRecipeInfo(@RequestBody DrugRecipe drugRecipe) {
        if(StringUtils.isEmpty(drugRecipe.getRecipeNo())) {
            return ResponseResult.error("方剂no不能为空");
        }
        if(StringUtils.isEmpty(drugRecipe.getId().toString())) {
            return ResponseResult.error("方剂id不能为空");
        }
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        drugRecipe.setUpdateName(user.getUserRealName());
        drugRecipe.setUpdateTime(new Date());
        int i = drugRecipeService.updateRecipeInfo(drugRecipe);
        logService.addLog(Action.UPDATE, Table.DRUG_PRICE, drugRecipe.getRecipeNo(),i > 0 ? Status.SUCCESS : Status.FAIL);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "保存新方剂")
    @PostMapping(value = "/save")
    public ResponseResult saveRecipeInfo(@RequestBody DrugRecipe drugRecipe) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        drugRecipe.setRecipeNo(NoGeneratorUtil.getNo());
        drugRecipe.setCreateName(user.getUserRealName());
        drugRecipe.setCreateTime(new Date());
        boolean save = drugRecipeService.save(drugRecipe);
        logService.addLog(Action.INSERT, Table.DRUG_PRICE, drugRecipe.getRecipeNo(),save ? Status.SUCCESS : Status.FAIL);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除方剂")
    @PostMapping("/delete")
    public ResponseResult deleteRecipe(@RequestBody DrugRecipe drugRecipe) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        logger.error("管理员{}正在进行方剂删除操作，药物no为{}",user.getUserName(),drugRecipe.getRecipeNo());
//        drugRecipeService.removeById(drugRecipe.getId());
        drugRecipe.setUpdateTime(new Date());
        drugRecipe.setUpdateName(user.getUserRealName());
        drugRecipeService.deleteRecipe(drugRecipe);
        logService.addLog(Action.DELETE, Table.DRUG_PRICE, drugRecipe.getRecipeNo());
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取已删除列表")
    @GetMapping("/deleteList")
    public ResponseResult getDeleteList() {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        List<RecipeInfo> deleteList = drugRecipeService.getDeleteList();
        return ResponseResult.ok().put(deleteList);
    }

    @ApiOperation(value = "恢复被删除的方剂")
    @PostMapping("/recover")
    public ResponseResult recoverProd(@RequestBody DrugRecipe drugRecipe) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        UserEntity user = (UserEntity) StpUtil.getSession().get("user");
        if(!user.getUserType().equals("admin")) {
            return ResponseResult.error("没有相关操作权限,请联系管理员！");
        }
        drugRecipe.setUpdateTime(new Date());
        drugRecipe.setUpdateName(user.getUserRealName());
        drugRecipeService.recoverRecipe(drugRecipe);
        logService.addLog(Action.RECOVER, Table.DRUG_PRICE, drugRecipe.getRecipeNo());
        return ResponseResult.ok();
    }
}
