package com.xiaoxing.train.member.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 乘车人列表分页查询响应类
 */
@Data
public class PassengerPageQueryResponse implements Serializable {
    /**
     * 乘车人响应实体类列表
     */
    private List<PassengerQueryResponse> passengerQueryResponseList;

    /**
     * 查询总数
     */
    private Long total;

    @Serial
    private static final long serialVersionUID = 1L;
}
