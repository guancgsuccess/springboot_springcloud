package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 标签数据访问层
 * JpaRepository - 提供了基本的增删改查
 * JpaSpecificationExecutor - 进行复杂的条件查询
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit> {

    /**
     * 推荐职位列表
     * 按状态查询
     * @param state
     * @return
     */
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);


    /**
     * 最新职位列表
     * 查询状态不为0并且按照日期降序排列,取前面12个数据
     * @param state
     * @return
     */
   List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
