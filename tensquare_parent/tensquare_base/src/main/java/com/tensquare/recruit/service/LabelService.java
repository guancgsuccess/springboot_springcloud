package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.LabelDao;
import com.tensquare.recruit.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签服务类
 */
@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 全部标签列表
     * @return
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /**
     * 增加标签
     * @param label
     */
    public void add(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
     * 修改标签
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }

    /**
     * 根据id进行删除
     * @param id
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    public Specification<Label> mySpecification(Label label){
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> predicates = new ArrayList<>();
                    //参数有效性判断
                    if(null!=label.getLabelname() && !"".equals(label.getLabelname())){
                        //Predicate predicate = cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
                        //return predicate;

                        predicates.add(cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%"));
                    }

                    if(null!=label.getState() && !"".equals(label.getState())){
                        predicates.add(cb.equal(root.get("state").as(String.class),label.getState()));
                    }
                    Predicate[] arr = new Predicate[predicates.size()];

                    return cb.and(predicates.toArray(arr));
                }
            };
        }

    /**
     * 构建查询条件
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label){
        return labelDao.findAll(mySpecification(label));
//        return labelDao.findAll(new Specification<Label>() {
//            /**
//             *
//             * @param root 根对象,也就是要把条件封装到哪个对象中.
//             * @param query 封装的都是查询关键字.比如group by order by等
//             * @param cb 用来封装条件对象的.如果直接返回null,则不需要任何条件
//             * @return
//             */
//            @Override
//            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                List<Predicate> predicates = new ArrayList<>();
//                //参数有效性判断
//                if(null!=label.getLabelname() && !"".equals(label.getLabelname())){
//                    //Predicate predicate = cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
//                    //return predicate;
//
//                    predicates.add(cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%"));
//                }
//
//                if(null!=label.getState() && !"".equals(label.getState())){
//                    predicates.add(cb.equal(root.get("state").as(String.class),label.getState()));
//                }
//                Predicate[] arr = new Predicate[predicates.size()];
//
//                return cb.and(predicates.toArray(arr));
//            }
//        });
    }

    /**
     * 条件分页查询
     * @param label
     * @param page 当前页
     * @param size 每页显示多少条
     * @return
     */
    public Page<Label> querySearch(Label label, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(mySpecification(label),pageable);
    }
}
