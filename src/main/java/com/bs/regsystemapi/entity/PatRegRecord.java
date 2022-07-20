package com.bs.regsystemapi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
  * @author  wgg
 * @date 2022-04-25 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="pat_regrecord")
public class PatRegRecord implements Serializable {

	private static final long serialVersionUID =  639915984715346337L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

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
	 * 医生no
	 */
   	@TableField(value = "doctor_no" )
	private String doctorNo;

	/**
	 * 就诊时间
	 */
   	@TableField(value = "visit_time" )
	private String visitTime;

	/**
	 * 订单状态，0待就诊,1已完成,2已取消,3未到
	 */
   	@TableField(value = "reg_state" )
	private String regState;

	/**
	 * 疾病描述
	 */
   	@TableField(value = "illness_detail" )
	private String illnessDetail;

	/**
	 * 预约状态，0预约失败，1预约成功
	 */
   	@TableField(value = "is_success" )
	private String isSuccess;

	/**
	 * 就诊日期
	 */
   	@TableField(value = "visit_data" )
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date visitData;

	/**
	 * 患者备注
	 */
   	@TableField(value = "person_remark" )
	private String personRemark;

	/**
	 * 创建日期
	 */
   	@TableField(value = "creat_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;

	/**
	 * 是否已删除，1：正常，0：已删除
	 */
   	@TableField(value = "is_delete" )
	private String isDelete;

}
