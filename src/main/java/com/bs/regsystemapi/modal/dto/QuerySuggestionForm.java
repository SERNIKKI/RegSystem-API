package com.bs.regsystemapi.modal.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/25 21:59
 */
@Data
public class QuerySuggestionForm implements Serializable {

    private String keyword;

    private String region;

    private String pageNum;

    private String pageSize;

}
