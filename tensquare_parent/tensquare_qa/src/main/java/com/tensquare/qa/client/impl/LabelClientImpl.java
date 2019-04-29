package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.LabelClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:熔断器
 * @date 2019/4/29 0029
 */
@Component
public class LabelClientImpl implements LabelClient{
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR,"熔断器启动了.");
    }
}
