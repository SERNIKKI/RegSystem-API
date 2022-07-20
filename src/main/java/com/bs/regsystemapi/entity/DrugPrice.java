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
 * @date 2022-03-19 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="drug_price")
public class DrugPrice implements Serializable {

	private static final long serialVersionUID =  3846113104282941451L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 药物no
	 */
   	@TableField(value = "drug_no" )
	private String drugNo;

	/**
	 * 药物类型，1：西药，2：中成药，3：中药材
	 */
   	@TableField(value = "drug_type" )
	private String drugType;

	/**
	 * 单价（非医保）
	 */
   	@TableField(value = "drug_price" )
	private String drugPrice;

	/**
	 * 单位
	 */
   	@TableField(value = "drug_unit" )
	private String drugUnit;

	/**
	 * 单价（有医保）
	 */
   	@TableField(value = "drug_price_insurance" )
	private String drugPriceInsurance;

	/**
	 * 历史最高价（非医保）
	 */
   	@TableField(value = "drug_max" )
	private String drugMax;

	/**
	 * 历史最高价（有医保）
	 */
   	@TableField(value = "drug_max_insurance" )
	private String drugMaxInsurance;

	/**
	 * 历史最低价（非医保）
	 */
   	@TableField(value = "drug_min" )
	private String drugMin;

	/**
	 * 历史最低价（有医保）
	 */
   	@TableField(value = "drug_min_insurance" )
	private String drugMinInsurance;

	/**
	 * 核准人
	 */
   	@TableField(value = "create_name" )
	private String createName;

	/**
	 * 核准日期
	 */
   	@TableField(value = "create_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 修订人
	 */
   	@TableField(value = "update_name" )
	private String updateName;

	/**
	 * 修订日期
	 */
   	@TableField(value = "update_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 是否参与医保
	 */
	@TableField(value = "is_insurance")
   	private String isInsurance;

}
