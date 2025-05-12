package com.xiaoxing.train.member.controller;

import com.xiaoxing.train.common.exception.BusinessException;
import com.xiaoxing.train.common.result.BaseResponse;
import com.xiaoxing.train.common.result.ErrorCode;
import com.xiaoxing.train.common.result.ResultUtils;
import com.xiaoxing.train.member.domain.Passenger;
import com.xiaoxing.train.member.req.PassengerSaveRequest;
import com.xiaoxing.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Passenger")
public class PassengerController {
    @Resource
    PassengerService passengerService;

    @GetMapping("/count")
    public BaseResponse<Long> count() {
        return ResultUtils.success(passengerService.count());
    }

    @PostMapping("/add")
    public BaseResponse<Passenger> add(@Valid @RequestBody PassengerSaveRequest passengerSaveRequest){
        Passenger passenger = passengerService.add(passengerSaveRequest);
        if (passenger==null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultUtils.success(passenger);
    }
}
