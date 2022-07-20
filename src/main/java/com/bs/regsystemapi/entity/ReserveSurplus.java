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
 * @date 2022-04-20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="reserve_surplus")
public class ReserveSurplus implements Serializable {

	private static final long serialVersionUID =  2113006099690580196L;

	@TableField(value = "total")
	private Long total;

   	@TableField(value = "id" )
	@TableId
	private Long id;

   	@TableField(value = "surplus_no" )
	private String surplusNo;

   	@TableField(value = "doctor_no" )
	private String doctorNo;

	/**
	 * 周日
	 */
   	@TableField(value = "sun_morning" )
	private Long sunMorning;

   	@TableField(value = "sun_afternoon" )
	private Long sunAfternoon;

	/**
	 * 0
	 */
   	@TableField(value = "sun_night" )
	private Long sunNight;

   	@TableField(value = "mon_morning" )
	private Long monMorning;

   	@TableField(value = "mon_afternoon" )
	private Long monAfternoon;

   	@TableField(value = "mon_night" )
	private Long monNight;

   	@TableField(value = "tue_morning" )
	private Long tueMorning;

   	@TableField(value = "tue_afternoon" )
	private Long tueAfternoon;

   	@TableField(value = "tue_night" )
	private Long tueNight;

   	@TableField(value = "wed_morning" )
	private Long wedMorning;

   	@TableField(value = "wed_afternoon" )
	private Long wedAfternoon;

   	@TableField(value = "wed_night" )
	private Long wedNight;

   	@TableField(value = "thur_morning" )
	private Long thurMorning;

   	@TableField(value = "thur_afternoon" )
	private Long thurAfternoon;

   	@TableField(value = "thur_night" )
	private Long thurNight;

   	@TableField(value = "fri_morning" )
	private Long friMorning;

   	@TableField(value = "fir_afternoon" )
	private Long firAfternoon;

   	@TableField(value = "fir_night" )
	private Long firNight;

   	@TableField(value = "sat_morning" )
	private Long satMorning;

   	@TableField(value = "sat_afternoon" )
	private Long satAfternoon;

   	@TableField(value = "sat_night" )
	private Long satNight;

   	@TableField(value = "update_name" )
	private String updateName;

   	@TableField(value = "update_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

}
