package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 吐槽控制层
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有的吐槽列表
     * @return
     */
    @GetMapping
    public Result findAll(){
        List<Spit> spitList = spitService.findAll();
        return new Result(true,StatusCode.OK,"吐槽列表",spitList);
    }

    /**
     * 根据id进行查询吐槽
     * @param spitId
     * @return
     */
    @GetMapping("/{spitId}")
    public Result findById(@PathVariable String spitId){
        Spit spit = spitService.getById(spitId);
        return new Result(true,StatusCode.OK,"根据id查询成功",spit);
    }

    /**
     * 增加吐槽
     * @param spit
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true, StatusCode.OK,"增加吐槽成功");
    }

    /**
     * 更新吐槽
     * @param spitId
     * @param spit
     * @return
     */
    @PutMapping("/{spitId}")
    public Result update(@PathVariable String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改吐槽");
    }

    /**
     * 根据id来进行删除
     * @param spitId
     */
    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true,StatusCode.OK,"根据id删除吐槽成功");
    }

    /**
     * 根据父级查询并且进行分页处理
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/comment/{parentId}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentId,@PathVariable int page,@PathVariable int size){
        Page<Spit> spitPage = spitService.findByParentId(parentId, page, size);

        return new Result(true,StatusCode.OK,"根据父级查询并且分页处理",new PageResult<>(spitPage.getTotalElements(),spitPage.getContent()));
    }

    /**
     * 吐槽点赞 - 已经点赞过的不允许继续点赞
     * @param spitId
     * @return
     */
    @PutMapping("/thumbup/{spitId}")
    public Result updateThumbup(@PathVariable String spitId){

        //模拟userid - 后边修改成当前登录的用户
        String userid = "1";

        if(redisTemplate.opsForValue().get("thumbup_"+userid+"_"+spitId)!=null){
            return new Result(false,StatusCode.REPERROR,"您已经点赞过了!");
        }
        spitService.updateThumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_"+userid+"_"+spitId,"1");
        return new Result(true,StatusCode.OK,"吐槽点赞");
    }
}
