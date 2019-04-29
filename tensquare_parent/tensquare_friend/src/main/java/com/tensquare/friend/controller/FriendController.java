package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import com.tensquare.friend.service.NoFriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:交友控制层
 * @date 2019/4/12 0012
 */
@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private NoFriendService noFriendService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserClient userClient;

    @PutMapping("/like/{friendid}/{type}")
    public Result addFriend(@PathVariable String friendid,@PathVariable String type){
        Claims claims = (Claims) request.getAttribute("user_claims");
        //判断是否登录
        if(claims == null){
            return new Result(false, StatusCode.ACCESSERROR,"没有权限添加!");
        }
        //如果是喜欢
        if("1".equals(type)){
            if(friendService.addFriend(claims.getId(),friendid) == 0){
                return new Result(false,StatusCode.REPERROR,"重复添加好友!");
            }
        }else{
            //不喜欢
            int flag = noFriendService.addNoFriend(claims.getId(),friendid);
            if(flag == 0){
                return new Result(false,StatusCode.REPERROR,"重复添加非好友!");
            }else if(flag == 1){
                return new Result(true,StatusCode.OK,"拉黑成功!");
            }
        }
        userClient.updatefanscountandfollowcount(claims.getId(),friendid,1);
        return new Result(true,StatusCode.OK,"添加好友成功!");
    }
}
