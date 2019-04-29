package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:交友数据访问层
 * @date 2019/4/12 0012
 */
public interface FriendDao extends JpaRepository<Friend,String>{

    /**
     * 添加好友数据
     * @param userid
     * @param friendid
     * @return
     */
    Friend findByUseridAndFriendid(String userid,String friendid);


    /**
     * 更新为互相喜欢
     * @param userid
     * @param friendid
     * @param islike
     */
    @Modifying
    @Query(value = "update tb_friend set islike = ? where userid = ? and friendid = ?",nativeQuery = true)
    void updateisLike(String islike,String userid,String friendid);
}
