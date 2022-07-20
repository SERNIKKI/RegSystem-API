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
 * @date 2022-03-14 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="drug")
public class Drug implements Serializable {

	private static final long serialVersionUID =  8148096053299654170L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 药品名称
	 */
   	@TableField(value = "drug_name" )
	private String drugName;

	/**
	 * 通用名称
	 */
   	@TableField(value = "drug_general_name" )
	private String drugGeneralName;

	/**
	 * 成分
	 */
   	@TableField(value = "drug_ingredient" )
	private String drugIngredient;

	/**
	 * 性状
	 */
   	@TableField(value = "drug_nature" )
	private String drugNature;

	/**
	 * 作用类别
	 */
   	@TableField(value = "drug_action" )
	private String drugAction;

   	@TableField(value = "drug_no" )
	private String drugNo;

	/**
	 * 适应症
	 */
   	@TableField(value = "drug_indication" )
	private String drugIndication;

	/**
	 * 规格
	 */
   	@TableField(value = "drug_spec" )
	private String drugSpec;

	/**
	 * 用法用量
	 */
   	@TableField(value = "drug_usage" )
	private String drugUsage;

	/**
	 * 不良反应
	 */
   	@TableField(value = "drug_adverse" )
	private String drugAdverse;

	/**
	 * 禁忌
	 */
   	@TableField(value = "drug_taboo" )
	private String drugTaboo;

	/**
	 * 注意事项
	 */
   	@TableField(value = "drug_cautions" )
	private String drugCautions;

	/**
	 * 药物相互作用
	 */
   	@TableField(value = "drug_interaction" )
	private String drugInteraction;

	/**
	 * 药理作用
	 */
   	@TableField(value = "drug_phar_effects" )
	private String drugPharEffects;

	/**
	 * 贮藏
	 */
   	@TableField(value = "drug_storage" )
	private String drugStorage;

	/**
	 * 包装
	 */
   	@TableField(value = "drug_package" )
	private String drugPackage;

	/**
	 * 有效期
	 */
   	@TableField(value = "drug_period_date" )
	private String drugPeriodDate;

	/**
	 * 执行标准
	 */
   	@TableField(value = "drug_standards" )
	private String drugStandards;

	/**
	 * 批准文号
	 */
   	@TableField(value = "drug_approval_num" )
	private String drugApprovalNum;

	/**
	 * 生产企业
	 */
   	@TableField(value = "drug_company" )
	private String drugCompany;

	/**
	 * 价格
	 */
   	@TableField(value = "drug_price" )
	private Long drugPrice;

	/**
	 * 修改人
	 */
   	@TableField(value = "update_name" )
	private String updateName;

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
	 * 药物类型 1：西药 2：中成药
	 */
	@TableField(value = "drug_genus")
	private String drugGenus;

	/**
	 * 药物图片
	 */
	@TableField(value = "drug_image")
	private String drugImage;

	@TableField(value = "is_delete")
	private String isDelete;
}
