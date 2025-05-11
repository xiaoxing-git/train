package com.xiaoxing.train.member.controller;

import com.xiaoxing.train.common.result.BaseResponse;
import com.xiaoxing.train.common.result.ResultUtils;
import com.xiaoxing.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Passenger")
public class PassengerController {
    @Resource
    PassengerService passengerService;

    @GetMapping("/count")
    public BaseResponse<Long> count() {
        return ResultUtils.success(passengerService.count());
    }

}
