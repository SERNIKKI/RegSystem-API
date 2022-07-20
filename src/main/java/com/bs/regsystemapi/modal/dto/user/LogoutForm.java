package com.bs.regsystemapi.modal.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author qpj
 * @date 2022/2/25 17:11
 */
@Data
public class LogoutForm {
    private String infoNo;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logoutTime;

    private Long loginOnline;
}
