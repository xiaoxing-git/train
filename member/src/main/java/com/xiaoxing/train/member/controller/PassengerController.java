package com.xiaoxing.train.member.controller;

import com.xiaoxing.train.common.context.LoginMemberContext;
import com.xiaoxing.train.common.exception.BusinessException;
import com.xiaoxing.train.common.result.BaseResponse;
import com.xiaoxing.train.common.result.ErrorCode;
import com.xiaoxing.train.common.result.ResultUtils;
import com.xiaoxing.train.member.domain.Passenger;
import com.xiaoxing.train.member.req.PassengerQueryRequest;
import com.xiaoxing.train.member.req.PassengerSaveRequest;
import com.xiaoxing.train.member.resp.PassengerPageQueryResponse;
import com.xiaoxing.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 乘车人接口
 */
@RestController
@RequestMapping("/Passenger")
public class PassengerController {
    @Resource
    PassengerService passengerService;

    /**
     * 获取乘车人总数
     * @return
     */
    @GetMapping("/count")
    public BaseResponse<Long> count() {
        return ResultUtils.success(passengerService.count());
    }

    /**
     * 新增乘车人
     * @param passengerSaveRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Passenger> add(@Valid @RequestBody PassengerSaveRequest passengerSaveRequest){
        Passenger passenger = passengerService.add(passengerSaveRequest);
        if (passenger==null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultUtils.success(passenger);
    }

    /**
     * 分页查询乘车人列表
     * @param passengerQueryRequest
     * @return
     */
    @GetMapping("/queryList")
    public BaseResponse<PassengerPageQueryResponse> queryList(@Valid PassengerQueryRequest passengerQueryRequest){
        //从上下文获取登录用户
        passengerQueryRequest.setMemberId(LoginMemberContext.getId());
        PassengerPageQueryResponse passengerPageQueryResponse = passengerService.queryList(passengerQueryRequest);
        if (passengerPageQueryResponse==null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultUtils.success(passengerPageQueryResponse);
    }
}
