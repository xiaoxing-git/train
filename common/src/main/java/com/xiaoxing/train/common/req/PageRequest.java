package com.xiaoxing.train.common.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分页配置
 */
@Data
public class PageRequest implements Serializable {
    /**
     * 页面大小
     */
    protected int pageSize=5;
    /**
     * 当前页码
     */
    protected int pageNum=1;
}
