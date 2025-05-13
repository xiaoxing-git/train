package com.xiaoxing.train.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxing.train.member.domain.Passenger;
import com.xiaoxing.train.member.req.PassengerQueryRequest;
import com.xiaoxing.train.member.req.PassengerSaveRequest;
import com.xiaoxing.train.member.resp.PassengerPageQueryResponse;


/**
* @author xiaoxing
* @description 针对表【passenger(乘车人)】的数据库操作Service
* @createDate 2025-05-11 22:08:18
*/
public interface PassengerService extends IService<Passenger> {

    /**
     * 新增乘车人
     * @param passengerSaveRequest
     * @return
     */
    Passenger add(PassengerSaveRequest passengerSaveRequest);

    /**
     * 分页查询乘车人列表
     * @param passengerQueryRequest
     * @return
     */
    PassengerPageQueryResponse queryList(PassengerQueryRequest passengerQueryRequest);
}
