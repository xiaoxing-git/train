package com.xiaoxing.train.member.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxing.train.common.context.LoginMemberContext;
import com.xiaoxing.train.common.exception.BusinessException;
import com.xiaoxing.train.common.result.ErrorCode;
import com.xiaoxing.train.member.domain.Passenger;
import com.xiaoxing.train.member.mapper.PassengerMapper;
import com.xiaoxing.train.member.req.PassengerQueryRequest;
import com.xiaoxing.train.member.req.PassengerSaveRequest;
import com.xiaoxing.train.member.resp.PassengerPageQueryResponse;
import com.xiaoxing.train.member.resp.PassengerQueryResponse;
import com.xiaoxing.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(IdUtil.getSnowflakeNextId());
        DateTime now = DateTime.now();
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        boolean saved = save(passenger);
        if (!saved){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return passenger;
    }


    /**
     * 分页查询
     * @param request
     * @return
     */
    @Override
    public PassengerPageQueryResponse queryList(PassengerQueryRequest request) {
        if (request==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //获取分页参数
        IPage<Passenger> page = new Page<>(request.getPageNum(), request.getPageSize());
        IPage<Passenger> paged = page(page);
        //分页查询
        List<Passenger> passengerList = paged.getRecords();
        List<PassengerQueryResponse> passengerQueryResponses = BeanUtil.copyToList(passengerList, PassengerQueryResponse.class);
        //返回分页响应类
        PassengerPageQueryResponse passengerPageQueryResponse = new PassengerPageQueryResponse();
        passengerPageQueryResponse.setPassengerQueryResponseList(passengerQueryResponses);
        passengerPageQueryResponse.setTotal(paged.getTotal());
        return passengerPageQueryResponse;
    }
}




