package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * 吐槽业务层
 */
@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 更新吐槽列表
     * @param spit
     */
    public void update(Spit spit){
        spitDao.save(spit);
    }

    /**
     * 根据id来删除吐槽
     * @param spitId
     */
    public void deleteById(String spitId){
        spitDao.deleteById(spitId);
    }

    /**
     * 吐槽列表
     * @return
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * 根据id进行吐槽查询
     * @param spitId
     * @return
     */
    public Spit getById(String spitId){
        return spitDao.findById(spitId).get();
    }

    /**
     * 增加吐槽
     * @param spit
     */
    public void add(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        //初始化一些默认的数据
        spit.setPublictime(new Date());
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态

        //判断有无父节点
        if(spit.getParentid()!=null && !"".equals(spit.getParentid())){
            //根据父节点来查询spit
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    /**
     *
     * @param parentId 父级id
     * @param page 当前页
     * @param size 每页显示多少条
     * @return
     */
    public Page<Spit> findByParentId(String parentId,int page,int size){
        PageRequest pageRequest = PageRequest.of(page-1,size);
        return spitDao.findByParentid(parentId,pageRequest);
    }

    /**
     * 吐槽点赞
     * @param id
     */
    public void updateThumbup(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
}
