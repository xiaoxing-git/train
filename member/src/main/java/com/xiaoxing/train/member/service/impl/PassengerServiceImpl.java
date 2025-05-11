package com.xiaoxing.train.member.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxing.train.member.domain.Passenger;
import com.xiaoxing.train.member.mapper.PassengerMapper;
import com.xiaoxing.train.member.service.PassengerService;
import org.springframework.stereotype.Service;

/**
* @author xiaoxing
* @description 针对表【passenger(乘车人)】的数据库操作Service实现
* @createDate 2025-05-11 22:08:18
*/
@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger>
    implements PassengerService{

}




