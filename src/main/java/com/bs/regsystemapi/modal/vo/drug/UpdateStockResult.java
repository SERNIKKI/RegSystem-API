package com.bs.regsystemapi.modal.vo.drug;

import com.bs.regsystemapi.modal.dto.drug.QueryAddStockForm;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/22 16:56
 */
@Data
public class UpdateStockResult implements Serializable {

    private List<QueryAddStockForm> error;

    private int totalError;

    private List<QueryAddStockForm> success;

    private int totalSuccess;
}
