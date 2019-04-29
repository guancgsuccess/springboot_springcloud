package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/4/13 0013
 */
@FeignClient("tensquare-user")
public interface UserClient {

    /**
     * 当用户点击了喜欢
     * 比如A关注了B,则A的关注数+1,B的粉丝数+1
     * @param userid
     * @param friendid
     * @param x
     */
    @PutMapping("/user/{userid}/{friendid}/{x}")
    void updatefanscountandfollowcount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid, @PathVariable("x") int x);
}
