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
@TableName (value ="pat_person")
public class PatPerson implements Serializable {

	private static final long serialVersionUID =  7263359512687843661L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 就诊卡号
	 */
   	@TableField(value = "person_no" )
	private String personNo;

	/**
	 * 就诊人姓名
	 */
   	@TableField(value = "person_name" )
	private String personName;

	/**
	 * 就诊人证件号
	 */
   	@TableField(value = "identify_card" )
	private String identifyCard;

	/**
	 * 就诊人手机号
	 */
   	@TableField(value = "person_phone" )
	private String personPhone;

	/**
	 * 就诊人性别
	 */
   	@TableField(value = "person_gender" )
	private String personGender;

	/**
	 * 就诊人出生日期
	 */
   	@TableField(value = "person_birth" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date personBirth;

	/**
	 * 就诊人年龄
	 */
   	@TableField(value = "person_age" )
	private String personAge;

	/**
	 * 就诊人住址
	 */
   	@TableField(value = "person_address" )
	private String personAddress;

	/**
	 * 就诊人籍贯
	 */
   	@TableField(value = "person_place" )
	private String personPlace;

	/**
	 * 就诊人民族
	 */
   	@TableField(value = "nationality" )
	private String nationality;

	/**
	 * 用户号
	 */
   	@TableField(value = "pat_no" )
	private String patNo;

	/**
	 * 是否已删除，1：正常，0：已删除
	 */
   	@TableField(value = "is_delete" )
	private String isDelete;

}
