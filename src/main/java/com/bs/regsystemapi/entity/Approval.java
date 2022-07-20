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
 * @date 2022-03-28 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="approval")
public class Approval implements Serializable {

	private static final long serialVersionUID =  601992273118188248L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 审批单号
	 */
   	@TableField(value = "approval_no" )
	private String approvalNo;

	/**
	 * 审批单类型
	 */
   	@TableField(value = "approval_type" )
	private String approvalType;

	/**
	 * 标题
	 */
   	@TableField(value = "approval_title" )
	private String approvalTitle;

	/**
	 * 审批内容
	 */
   	@TableField(value = "approval_contact" )
	private String approvalContact;

	/**
	 * 申请人
	 */
   	@TableField(value = "approval_user" )
	private String approvalUser;

	/**
	 * 申请时间
	 */
   	@TableField(value = "approval_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date approvalTime;

	/**
	 * 处理时间
	 */
   	@TableField(value = "approval_end_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date approvalEndTime;

	/**
	 * 处理状态
	 */
   	@TableField(value = "approval_status" )
	private String approvalStatus;

	/**
	 * 处理人
	 */
   	@TableField(value = "approval_name" )
	private String approvalName;

	/**
	 * 是否已删除
	 */
   	@TableField(value = "is_delete" )
	private String isDelete;

	/**
	 * 是否已撤回
	 */
   	@TableField(value = "is_revoke" )
	private String isRevoke;

	/**
	 * 备注
	 */
	@TableField(value = "approval_remark")
   	private String approvalRemark;

	/**
	 * 回执
	 */
	@TableField(value = "approval_reply")
	private String approvalReply;

}
