package com.tensquare.qa.client;

import com.tensquare.qa.client.impl.LabelClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:调用另外一个服务中的方法.
 * @date 2019/4/10 0010
 */
@FeignClient(value = "tensquare-base",fallback = LabelClientImpl.class)
public interface LabelClient {

    /**
     * 此处的{labelId和@PathVariable中的参数以及接口方法的参数需要高度保持一致}
     * @param labelId
     * @return
     */
    @GetMapping(value = "/label/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId);
}
