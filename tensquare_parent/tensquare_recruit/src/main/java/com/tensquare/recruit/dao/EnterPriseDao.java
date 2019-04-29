package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 企业数据访问层
 * JpaRepository - 提供了基本的增删改查
 * JpaSpecificationExecutor - 进行复杂的条件查询
 *
 */
public interface EnterPriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise> {

    /**
     * 根据热门状态来获取企业信息
     * @param ishot
     * @return
     */
    List<Enterprise> findByIshot(String ishot);
}
