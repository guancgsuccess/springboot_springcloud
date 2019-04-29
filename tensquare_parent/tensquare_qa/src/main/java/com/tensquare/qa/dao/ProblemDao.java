package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem> {

    /**
     * 最新回答列表
     * reply回复数是0
     * @param labelId
     * @param pageable
     * @return
     */

    @Query("select p from Problem p join Pl pl on p.id = pl.problemid where pl.labelid =?1 order by replytime desc")
    Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);

    /**
     * 根据标签id查询某个标签下的热门的回答的问题
     * 热门回答 - 根据回复数来降序排列
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem p where p.id in (select problemid from tb_pl where labelid = ?) order by p.reply desc",nativeQuery = true)
    Page<Problem> findHotListByLabelId(String labelid,Pageable pageable);

    /**
     * 等待回答列表
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem p where p.id in (select problemid from tb_pl where labelid = ?1) and p.reply=0 order by p.createtime desc",nativeQuery = true)
    Page<Problem> findWaitListByLabelId(String labelid,Pageable pageable);
}
