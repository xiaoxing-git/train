package com.xiaoxing.train.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxing.train.member.domain.Passenger;
import com.xiaoxing.train.member.req.PassengerSaveRequest;


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
}
