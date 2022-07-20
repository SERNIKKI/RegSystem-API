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
  * @author  wgg
 * @date 2022-04-25 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="pat_payrecord")
public class PatPayRecord implements Serializable {

	private static final long serialVersionUID =  2206754138248401270L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 用户no
	 */
   	@TableField(value = "pat_no" )
	private String patNo;

	/**
	 * 医生no
	 */
   	@TableField(value = "doctor_no" )
	private String doctorNo;

	/**
	 * 挂号费用
	 */
   	@TableField(value = "user_cost" )
	private Double userCost;

	/**
	 * 订单号
	 */
   	@TableField(value = "order_no" )
	private String orderNo;

	/**
	 * 订单状态，0待付款1已付款
	 */
   	@TableField(value = "order_state" )
	private String orderState;

	/**
	 * 付款类型
	 */
   	@TableField(value = "pay_type" )
	private String payType;

	/**
	 * 支付方式
	 */
   	@TableField(value = "pay_mode" )
	private String payMode;

	/**
	 * 缴费时间
	 */
   	@TableField(value = "pay_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payTime;

	/**
	 * 就诊卡号
	 */
   	@TableField(value = "person_no" )
	private String personNo;

	/**
	 * 预约号
	 */
   	@TableField(value = "reg_no" )
	private String regNo;

	/**
	 * 订单创建时间
	 */
   	@TableField(value = "creat_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;

	/**
	 * 订单完成时间
	 */
   	@TableField(value = "finish_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date finishTime;

	/**
	 * 是否已删除，1：正常，0：已删除
	 */
   	@TableField(value = "is_delete" )
	private String isDelete;

}
