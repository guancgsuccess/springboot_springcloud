package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/4/13 0013
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String>{
    /**
     * 根据userid和friendid进行查询,避免重复添加
     * @param userid
     * @param friendid
     * @return
     */
    NoFriend findByUseridAndFriendid(String userid,String friendid);
}
