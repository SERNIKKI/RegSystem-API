package com.bs.regsystemapi.modal.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author qpj
 * @date 2022/3/4 9:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateReadStatusForm {
    private String actorNo;

    private String receiverNo;
}
