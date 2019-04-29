package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Label;
import com.tensquare.recruit.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  标签控制层
 */
@CrossOrigin
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询所有的标签列表
     * @return
     */
    @GetMapping
    public Result findAll(){
        //获取头信息 - 测试微服务网关转发头丢失
        System.out.println(request.getHeader("authorizations"));
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    /**
     * 增加一个标签
     * @param label
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"添加标签成功");
    }

    /**
     * 根据标签id进行查询
     * @param labelId
     * @return
     */
    @GetMapping(value = "/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId){
        //故意写个错误,测试全局异常处理是否有效
       // int i = 10/0;

        Label label = labelService.findById(labelId);
        System.out.println(label);
        return new Result(true,StatusCode.OK,"查询id成功",label);
    }

    /**
     * 根据标签id进行修改
     * @param labelId
     * @return
     */
    @PutMapping("/{labelId}")
    public Result update(@PathVariable String labelId,@RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 根据标签id进行删除
     * @param labelId
     * @return
     */
    @DeleteMapping(value = "/{labelId}")
    //@RequestMapping(value = "/{labelId}",method= RequestMethod.DELETE)
    public Result deleteById(@PathVariable("labelId") String labelId){
        labelService.deleteById(labelId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 根据条件进行查询
     * @param label
     * @return
     */
    @PostMapping("/{search}")
    public Result findSearch(@RequestBody Label label){
        List<Label> labelList = labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"条件查询成功",labelList);
    }

    /**
     * 条件分页进行查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result querySearch(@RequestBody Label label,@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Page<Label> labelList = labelService.querySearch(label,page,size);

        return new Result(true,StatusCode.OK,"条件分页查询",new PageResult<>(labelList.getTotalElements(),labelList.getContent()));
    }
}
