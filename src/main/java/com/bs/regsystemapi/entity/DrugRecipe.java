package com.bs.regsystemapi.entity;

import java.io.Serializable;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
  * @author  qpj
 * @date 2022-03-17 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="drug_recipe")
public class DrugRecipe implements Serializable {

	private static final long serialVersionUID =  4693951322326815437L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

   	@TableField(value = "recipe_no" )
	private String recipeNo;

	/**
	 * 方名
	 */
   	@TableField(value = "recipe_name" )
	private String recipeName;

	/**
	 * 汉语拼音
	 */
   	@TableField(value = "recipe_pinyin" )
	private String recipePinyin;

	/**
	 * 主要成分
	 */
   	@TableField(value = "recipe_ingredient" )
	private String recipeIngredient;

	/**
	 * 作用类别
	 */
   	@TableField(value = "recipe_action" )
	private String recipeAction;

	/**
	 * 组成
	 */
   	@TableField(value = "recipe_composed" )
	private String recipeComposed;

	/**
	 * 用法
	 */
   	@TableField(value = "recipe_apply" )
	private String recipeApply;

	/**
	 * 功用
	 */
   	@TableField(value = "recipe_efficacy" )
	private String recipeEfficacy;

	/**
	 * 主治
	 */
   	@TableField(value = "recipe_indication" )
	private String recipeIndication;

	/**
	 * 配伍特点
	 */
   	@TableField(value = "recipe_feature" )
	private String recipeFeature;

	/**
	 * 鉴别
	 */
   	@TableField(value = "recipe_ident" )
	private String recipeIdent;

	/**
	 * 辩证要点
	 */
   	@TableField(value = "recipe_PA" )
	private String recipePa;

	/**
	 * 加减变化
	 */
   	@TableField(value = "recipe_AOSC" )
	private String recipeAosc;

	/**
	 * 现代运用
	 */
   	@TableField(value = "recipe_MU" )
	private String recipeMu;

	/**
	 * 使用注意
	 */
   	@TableField(value = "recipe_note" )
	private String recipeNote;

	/**
	 * 入库时间
	 */
   	@TableField(value = "create_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 入库人
	 */
   	@TableField(value = "create_name" )
	private String createName;

	/**
	 * 修改时间
	 */
   	@TableField(value = "update_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 修改人
	 */
   	@TableField(value = "update_name" )
	private String updateName;

	/**
	 * 方剂图片
	 */
   	@TableField(value = "recipe_image" )
	private String recipeImage;

   	@TableField(value = "recipe_origin")
   	private String recipeOrigin;

	@TableField(value = "is_delete")
	private String isDelete;
}
