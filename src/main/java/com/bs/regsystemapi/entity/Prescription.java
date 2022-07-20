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
@TableName (value ="prescription")
public class Prescription implements Serializable {

	private static final long serialVersionUID =  3787959189246139660L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 处方单号
	 */
   	@TableField(value = "prescription_no" )
	private String prescriptionNo;

	/**
	 * 医生no
	 */
   	@TableField(value = "doctor_no" )
	private String doctorNo;

	/**
	 * 就诊卡no
	 */
   	@TableField(value = "person_no" )
	private String personNo;

	/**
	 * 患者no
	 */
   	@TableField(value = "pat_no" )
	private String patNo;

	/**
	 * 预约号
	 */
   	@TableField(value = "reg_no" )
	private String regNo;

	/**
	 * 患者身高
	 */
   	@TableField(value = "person_height" )
	private String personHeight;

	/**
	 * 患者体重
	 */
   	@TableField(value = "person_weight" )
	private String personWeight;

	/**
	 * 患者职业
	 */
   	@TableField(value = "person_occupation" )
	private String personOccupation;

	/**
	 * 婚姻状况
	 */
   	@TableField(value = "person_marital_status" )
	private String personMaritalStatus;

	/**
	 * 户口地址
	 */
   	@TableField(value = "person_origin_addrress" )
	private String personOriginAddrress;

	/**
	 * 工作单位
	 */
   	@TableField(value = "person_work_address" )
	private String personWorkAddress;

	/**
	 * 过敏史
	 */
   	@TableField(value = "person_allergic_history" )
	private String personAllergicHistory;

	/**
	 * 既往病史
	 */
   	@TableField(value = "person_past_medical_history" )
	private String personPastMedicalHistory;

	/**
	 * 备注
	 */
   	@TableField(value = "person_remark" )
	private String personRemark;

	/**
	 * 入院时间
	 */
   	@TableField(value = "consultation_time" )
	private String consultationTime;

	/**
	 * 是否有医保 1：有，0：没有
	 */
   	@TableField(value = "is_have_insurance" )
	private String isHaveInsurance;

	/**
	 * 医保号
	 */
   	@TableField(value = "insurance_no" )
	private String insuranceNo;

	/**
	 * 临床诊断
	 */
   	@TableField(value = "diagnosis" )
	private String diagnosis;

	/**
	 * 药物列表no
	 */
   	@TableField(value = "drugs_no" )
	private String drugsNo;

	/**
	 * 总价
	 */
   	@TableField(value = "total_price" )
	private Double totalPrice;

	/**
	 * 门诊订单no
	 */
   	@TableField(value = "order_no" )
	private String orderNo;

	/**
	 * 创建时间
	 */
   	@TableField(value = "creat_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;

   	@TableField(value = "is_delete" )
	private String isDelete;

	/**
	 * 状态 0：已创建，1：已完成  2：未完成，已过期
	 */
   	@TableField(value = "prescription_status" )
	private String prescriptionStatus;

}
