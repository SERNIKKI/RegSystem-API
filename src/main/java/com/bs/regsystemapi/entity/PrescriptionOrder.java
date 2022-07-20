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
 * @date 2022-05-06
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="prescription_order")
public class PrescriptionOrder implements Serializable {

	private static final long serialVersionUID =  5315847834893427310L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 订单号
	 */
   	@TableField(value = "order_no" )
	private String orderNo;

	/**
	 * 就诊单号
	 */
   	@TableField(value = "prescription_no" )
	private String prescriptionNo;

	/**
	 * 订单金额
	 */
   	@TableField(value = "order_price" )
	private Double orderPrice;

	/**
	 * 订单状态 0：已创建，未支付，1：已支付，2：已过期
	 */
   	@TableField(value = "order_status" )
	private String orderStatus;

	/**
	 * 支付方式
	 */
   	@TableField(value = "pay_way" )
	private String payWay;

	/**
	 * 支付日期
	 */
   	@TableField(value = "pay_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payTime;

	/**
	 * 订单创建日期
	 */
   	@TableField(value = "create_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 备注
	 */
   	@TableField(value = "order_remark" )
	private String orderRemark;

	/**
	 * 是否删除
	 */
	@TableField(value = "is_delete" )
	private String isDelete;

}
