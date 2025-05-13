package com.xiaoxing.train.member.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 乘车人查询响应类
 * @TableName passenger
 */

@Data
public class PassengerQueryResponse implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCode;

    /**
     * 旅客类型
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    @Serial
    private static final long serialVersionUID = 1L;

}