package com.tensquare.recruit.service;

import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tensquare.recruit.dao.EnterPriseDao;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 企业业务类.
 */
@Service
@Transactional
public class EnterPriseService {

    @Autowired
    private EnterPriseDao enterPriseDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询热门企业
     * @return
     */
    public List<Enterprise> findByIshot(){
        return enterPriseDao.findByIshot("1");
    }

    /**
     * 企业查询列表
     * @return
     */
    public List<Enterprise> findAll(){
        return enterPriseDao.findAll();
    }

    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    public List<Enterprise> findSearch(Map whereMap){
        return enterPriseDao.findAll(createSpecification(whereMap));
    }

    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Enterprise> findSearch(Map whereMap, int page, int size) {
        Specification<Enterprise> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return enterPriseDao.findAll(specification, pageRequest);
    }

    /**
     * 增加
     * @param enterprise
     */
    public void add(Enterprise enterprise) {
        enterprise.setId(idWorker.nextId()+"");
        enterPriseDao.save(enterprise);
    }

    /**
     * 修改
     * @param enterprise
     */
    public void update(Enterprise enterprise) {
        enterPriseDao.save(enterprise);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id) {
        enterPriseDao.deleteById(id);
    }

    /**
     * 根据ID查询实体
     * @param id
     * @return
     */
    public Enterprise findById(String id) {
        return enterPriseDao.findById(id).get();
    }

    /**
     * 动态条件构建
     * @param searchMap
     * @return
     */
    private Specification<Enterprise> createSpecification(Map searchMap) {
        return new Specification<Enterprise>() {
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 企业名称
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 企业简介
                if (searchMap.get("summary")!=null && !"".equals(searchMap.get("summary"))) {
                    predicateList.add(cb.like(root.get("summary").as(String.class), "%"+(String)searchMap.get("summary")+"%"));
                }
                // 企业地址
                if (searchMap.get("address")!=null && !"".equals(searchMap.get("address"))) {
                    predicateList.add(cb.like(root.get("address").as(String.class), "%"+(String)searchMap.get("address")+"%"));
                }
                // 标签列表
                if (searchMap.get("labels")!=null && !"".equals(searchMap.get("labels"))) {
                    predicateList.add(cb.like(root.get("labels").as(String.class), "%"+(String)searchMap.get("labels")+"%"));
                }
                // 坐标
                if (searchMap.get("coordinate")!=null && !"".equals(searchMap.get("coordinate"))) {
                    predicateList.add(cb.like(root.get("coordinate").as(String.class), "%"+(String)searchMap.get("coordinate")+"%"));
                }
                // 是否热门
                if (searchMap.get("ishot")!=null && !"".equals(searchMap.get("ishot"))) {
                    predicateList.add(cb.like(root.get("ishot").as(String.class), "%"+(String)searchMap.get("ishot")+"%"));
                }
                // LOGO
                if (searchMap.get("logo")!=null && !"".equals(searchMap.get("logo"))) {
                    predicateList.add(cb.like(root.get("logo").as(String.class), "%"+(String)searchMap.get("logo")+"%"));
                }
                // URL
                if (searchMap.get("url")!=null && !"".equals(searchMap.get("url"))) {
                    predicateList.add(cb.like(root.get("url").as(String.class), "%"+(String)searchMap.get("url")+"%"));
                }
                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
