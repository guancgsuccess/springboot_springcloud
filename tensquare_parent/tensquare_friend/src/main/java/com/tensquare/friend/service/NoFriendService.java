package com.tensquare.friend.service;

import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/4/13 0013
 */
@Service
@Transactional
public class NoFriendService {

    @Autowired
    private NoFriendDao noFriendDao;

    public int addNoFriend(String userid,String friendid){
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid,friendid);

        //判断是否已经存在
        if(noFriend!=null){
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }
}
