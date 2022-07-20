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
 * @date 2022-03-16 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="drug_prod")
public class DrugProd implements Serializable {

	private static final long serialVersionUID =  1707486644405373961L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

   	@TableField(value = "prod_no" )
	private String prodNo;

	/**
	 * 生产企业
	 */
   	@TableField(value = "prod_name" )
	private String prodName;

	/**
	 * 生产地址
	 */
   	@TableField(value = "prod_address" )
	private String prodAddress;

	/**
	 * 邮政编码
	 */
   	@TableField(value = "prod_zip" )
	private String prodZip;

	/**
	 * 传真号码
	 */
   	@TableField(value = "prod_fax" )
	private String prodFax;

	/**
	 * 服务热线
	 */
   	@TableField(value = "prod_hotline" )
	private String prodHotline;

	/**
	 * 网址
	 */
   	@TableField(value = "prod_website" )
	private String prodWebsite;

	/**
	 * 邮箱
	 */
   	@TableField(value = "prod_email" )
	private String prodEmail;

	/**
	 * 电话
	 */
   	@TableField(value = "prod_tel" )
	private String prodTel;

	/**
	 * 省份/国家
	 */
   	@TableField(value = "prod_province" )
	private String prodProvince;

	/**
	 * 入库人
	 */
   	@TableField(value = "create_name" )
	private String createName;

	/**
	 * 入库时间
	 */
   	@TableField(value = "create_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 修改人
	 */
   	@TableField(value = "update_name" )
	private String updateName;

	/**
	 * 修改时间
	 */
   	@TableField(value = "update_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	@TableField(value = "is_delete")
	private String isDelete;
}
