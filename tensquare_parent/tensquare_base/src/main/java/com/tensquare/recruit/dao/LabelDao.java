package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 标签数据访问层
 * JpaRepository - 提供了基本的增删改查
 * JpaSpecificationExecutor - 进行复杂的条件查询
 *
 */
public interface LabelDao extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label> {

}
