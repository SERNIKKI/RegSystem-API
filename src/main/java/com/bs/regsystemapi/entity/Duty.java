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
 * @date 2022-03-27 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="duty")
public class Duty implements Serializable {

	private static final long serialVersionUID =  3931988394969676767L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

   	@TableField(value = "duty_no" )
	private String dutyNo;

   	@TableField(value = "doctor_no" )
	private String doctorNo;

	/**
	 * 周日
	 */
   	@TableField(value = "sun_morning" )
	private String sunMorning;

	/**
	 * 周日
	 */
   	@TableField(value = "sun_afternoon" )
	private String sunAfternoon;

	/**
	 * 周日
	 */
   	@TableField(value = "sun_night" )
	private String sunNight;

	/**
	 * 周一
	 */
   	@TableField(value = "mon_morning" )
	private String monMorning;

	/**
	 * 周一
	 */
   	@TableField(value = "mon_afternoon" )
	private String monAfternoon;

	/**
	 * 周一
	 */
   	@TableField(value = "mon_night" )
	private String monNight;

	/**
	 * 周二
	 */
   	@TableField(value = "tue_morning" )
	private String tueMorning;

	/**
	 * 周二
	 */
   	@TableField(value = "tue_afternoon" )
	private String tueAfternoon;

	/**
	 * 周二
	 */
   	@TableField(value = "tue_night" )
	private String tueNight;

	/**
	 * 周三
	 */
   	@TableField(value = "wed_morning" )
	private String wedMorning;

	/**
	 * 周三
	 */
   	@TableField(value = "wed_afternoon" )
	private String wedAfternoon;

	/**
	 * 周三
	 */
   	@TableField(value = "wed_night" )
	private String wedNight;

	/**
	 * 周四
	 */
   	@TableField(value = "thur_morning" )
	private String thurMorning;

	/**
	 * 周四
	 */
   	@TableField(value = "thur_afternoon" )
	private String thurAfternoon;

	/**
	 * 周四
	 */
   	@TableField(value = "thur_night" )
	private String thurNight;

	/**
	 * 周五
	 */
   	@TableField(value = "fri_morning" )
	private String friMorning;

	/**
	 * 周五
	 */
   	@TableField(value = "fir_afternoon" )
	private String firAfternoon;

	/**
	 * 周五
	 */
   	@TableField(value = "fir_night" )
	private String firNight;

	/**
	 * 周六
	 */
   	@TableField(value = "sat_morning" )
	private String satMorning;

	/**
	 * 周六
	 */
   	@TableField(value = "sat_afternoon" )
	private String satAfternoon;

	/**
	 * 周六
	 */
   	@TableField(value = "sat_night" )
	private String satNight;

	/**
	 * 早上开始
	 */
   	@TableField(value = "morning_begin" )
	@JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date morningBegin;

	/**
	 * 早上结束
	 */
   	@TableField(value = "morning_end" )
	@JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date morningEnd;

	/**
	 * 下午开始
	 */
   	@TableField(value = "afternoon_begin" )
	@JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date afternoonBegin;

	/**
	 * 下午结束
	 */
   	@TableField(value = "afternoon_end" )
	@JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date afternoonEnd;

	/**
	 * 晚上开始
	 */
   	@TableField(value = "night_begin" )
	@JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date nightBegin;

	/**
	 * 晚上结束
	 */
   	@TableField(value = "night_end" )
	@JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date nightEnd;

	/**
	 * 排班人
	 */
   	@TableField(value = "update_name" )
	private String updateName;

	/**
	 * 排班时间
	 */
   	@TableField(value = "update_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

}
