package com.bs.regsystemapi.modal.vo.drug;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/19 17:57
 */
@Data
public class RecipeInfo implements Serializable {

    private Long id;

    private String recipeNo;

    /**
     * 方名
     */
    private String recipeName;

    /**
     * 汉语拼音
     */
    private String recipePinyin;

    /**
     * 主要成分
     */
    private String recipeIngredient;

    /**
     * 作用类别
     */
    private String recipeAction;

    /**
     * 组成
     */
    private String recipeComposed;

    /**
     * 用法
     */
    private String recipeApply;

    /**
     * 功用
     */
    private String recipeEfficacy;

    /**
     * 主治
     */
    private String recipeIndication;

    /**
     * 配伍特点
     */
    private String recipeFeature;

    /**
     * 鉴别
     */
    private String recipeIdent;

    /**
     * 辩证要点
     */
    private String recipePa;

    /**
     * 加减变化
     */
    private String recipeAosc;

    /**
     * 现代运用
     */
    private String recipeMu;

    /**
     * 使用注意
     */
    private String recipeNote;

    /**
     * 入库时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 入库人
     */
    private String createName;

    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateName;

    /**
     * 方剂图片
     */
    private String recipeImage;

    private String recipeOrigin;

    private String mainType;

    private String subType;
}
