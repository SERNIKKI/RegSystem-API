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
@TableName (value ="drug_herbs")
public class DrugHerbs implements Serializable {

	private static final long serialVersionUID =  4003074593281698443L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 药材名称(通用名称)
	 */
   	@TableField(value = "herb_name" )
	private String herbName;

	/**
	 * 汉语拼音
	 */
   	@TableField(value = "herb_pinyin" )
	private String herbPinyin;

	/**
	 * 作用类别
	 */
   	@TableField(value = "herb_action" )
	private String herbAction;

	/**
	 * 古籍出处
	 */
   	@TableField(value = "herb_origin" )
	private String herbOrigin;

	/**
	 * 基元
	 */
   	@TableField(value = "herb_fundamental" )
	private String herbFundamental;

	/**
	 * 主要产地
	 */
   	@TableField(value = "herb_place" )
	private String herbPlace;

	/**
	 * 采集
	 */
   	@TableField(value = "herb_collect" )
	private String herbCollect;

	/**
	 * 炮制
	 */
   	@TableField(value = "herb_concoct" )
	private String herbConcoct;

	/**
	 * 贮藏
	 */
   	@TableField(value = "herb_storage" )
	private String herbStorage;

	/**
	 * 药性
	 */
   	@TableField(value = "herb_prop" )
	private String herbProp;

	/**
	 * 归经
	 */
   	@TableField(value = "herb_return" )
	private String herbReturn;

	/**
	 * 应用
	 */
   	@TableField(value = "herb_apply" )
	private String herbApply;

	/**
	 * 功效
	 */
   	@TableField(value = "herb_efficacy" )
	private String herbEfficacy;

	/**
	 * 用法用量
	 */
   	@TableField(value = "herb_usage" )
	private String herbUsage;

	/**
	 * 古籍摘要
	 */
   	@TableField(value = "herb_abstract" )
	private String herbAbstract;

	/**
	 * 化学成分
	 */
   	@TableField(value = "herb_ingredient" )
	private String herbIngredient;

	/**
	 * 创建日期
	 */
   	@TableField(value = "create_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 创建人
	 */
   	@TableField(value = "create_name" )
	private String createName;

	/**
	 * 更新日期
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
	 * 药材图片
	 */
   	@TableField(value = "herb_image" )
	private String herbImage;

   	@TableField(value = "herb_no" )
	private String herbNo;

   	@TableField(value = "herb_adverse")
   	private String herbAdverse;

   	@TableField(value = "herb_remark")
   	private String herbRemark;

   	@TableField(value = "herb_ident")
   	private String herbIdent;

	@TableField(value = "is_delete")
	private String isDelete;
}
