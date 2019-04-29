package com.tensquare.recruit.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterPriseService;

import java.util.Map;

/**
 * 企业控制层
 */
@RestController
@RequestMapping("/enterprise")
public class EnterPriseController {

    @Autowired
    private EnterPriseService enterPriseService;

    /**
     * 查询所有的热门企业
     * @return
     */
    @GetMapping("/search/hotlist")
    public Result findByIshot(){
        return new Result(true, StatusCode.OK,"热门企业",enterPriseService.findByIshot());
    }

    /**
     * 查询所有的热门企业
     * @return
     */
    @GetMapping
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",enterPriseService.findAll());
    }

    /**
     * 根据条件进行查询
     * @param whereMap
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map whereMap){
        return new Result(true,StatusCode.OK,"条件查询成功",enterPriseService.findSearch(whereMap));
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",enterPriseService.findById(id));
    }


    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Enterprise> pageList = enterPriseService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Enterprise>(pageList.getTotalElements(), pageList.getContent()) );
    }


    /**
     * 增加
     * @param enterprise
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody Enterprise enterprise){
        enterPriseService.add(enterprise);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param enterprise
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody Enterprise enterprise, @PathVariable String id ){
        enterprise.setId(id);
        enterPriseService.update(enterprise);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String id ){
        enterPriseService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
