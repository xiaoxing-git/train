package com.xiaoxing.train.member.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxing.train.common.exception.BusinessException;
import com.xiaoxing.train.common.result.ErrorCode;
import com.xiaoxing.train.member.domain.Passenger;
import com.xiaoxing.train.member.mapper.PassengerMapper;
import com.xiaoxing.train.member.req.PassengerSaveRequest;
import com.xiaoxing.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author xiaoxing
* @description 针对表【passenger(乘车人)】的数据库操作Service实现
* @createDate 2025-05-11 22:08:18
*/
@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger>
    implements PassengerService{

    @Resource
    private PassengerMapper passengerMapper;
    /**
     * 新增乘车人
     * @param passengerSaveRequest
     * @return
     */
    @Override
    public Passenger add(PassengerSaveRequest passengerSaveRequest) {
        //判断乘车人是否已经添加过
        QueryWrapper<Passenger> wrapper = new QueryWrapper<>();
        Passenger selected = passengerMapper.selectOne(wrapper.eq("id_code", passengerSaveRequest.getIdCode()));
        if (selected!=null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"该乘车人已经添加过了");
        }
        Passenger passenger = BeanUtil.copyProperties(passengerSaveRequest, Passenger.class);
        passenger.setId(IdUtil.getSnowflakeNextId());
        passenger.setMemberId(1L);
        DateTime now = DateTime.now();
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        boolean saved = save(passenger);
        if (!saved){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return passenger;
    }
}




