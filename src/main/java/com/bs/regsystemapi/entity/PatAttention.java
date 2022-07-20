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
@TableName (value ="pat_attention")
public class PatAttention implements Serializable {

	private static final long serialVersionUID =  2418646241845536800L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 用户号
	 */
   	@TableField(value = "pat_no" )
	private String patNo;

	/**
	 * 医生no
	 */
   	@TableField(value = "user_no" )
	private String userNo;

	/**
	 * no
	 */
   	@TableField(value = "attention_no" )
	private String attentionNo;

	/**
	 * 关注时间
	 */
   	@TableField(value = "create_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 关注状态，1：正在关注，0：已取消关注
	 */
   	@TableField(value = "status" )
	private String status;

}
