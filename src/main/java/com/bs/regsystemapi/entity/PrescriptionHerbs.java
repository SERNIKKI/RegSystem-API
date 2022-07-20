package com.bs.regsystemapi.entity;

import java.io.Serializable;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
  * @author  qpj
 * @date 2022-05-06
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="prescription_herbs")
public class PrescriptionHerbs implements Serializable {

	private static final long serialVersionUID =  3187457146727403775L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

   	@TableField(value = "herbs_no" )
	private String herbsNo;

	/**
	 * 药物no
	 */
   	@TableField(value = "drug_no" )
	private String drugNo;

	/**
	 * 药物类型，1：西药，2：中药
	 */
   	@TableField(value = "drug_type" )
	private String drugType;

	/**
	 * 数量
	 */
   	@TableField(value = "num" )
	private Long num;

	/**
	 * 处方单号
	 */
   	@TableField(value = "prescription_no" )
	private String prescriptionNo;

	/**
	 * 价格
	 */
   	@TableField(value = "price" )
	private Double price;

	/**
	 * 价格
	 */
	@TableField(value = "unit_price" )
	private Double unitPrice;
}
