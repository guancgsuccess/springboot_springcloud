package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:交友业务类
 * @date 2019/4/12 0012
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    /**
     * 添加好友
     */
    public int addFriend(String userid,String friendid){
        //先判断userid到friendid中是否有数据,有就是重复添加好友,返回0
        Friend friend = friendDao.findByUseridAndFriendid(userid,friendid);
        if(null!=friend){
            return 0;//说明重复添加好友了
        }
        //直接添加好友,让好友表中userid到friend的方向为0
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);

        //判断从friendid到userid是否有数据,如果有把双方的islike更新为"1"
        if(friendDao.findByUseridAndFriendid(friendid,userid)!=null){
            friendDao.updateisLike("1",userid,friendid);
            friendDao.updateisLike("1",friendid,userid);
        }
        return 1;
    }
}
