package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 检索控制器
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    /**
     * 增加文章索引库
     * @param article
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Article article){
        articleSearchService.add(article);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    /**
     * 文章检索
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/search/{keywords}/{page}/{size}")
    public Result findByKeyWords(@PathVariable String keywords,@PathVariable int page,@PathVariable int size){
        Page<Article> pageArticle = articleSearchService.findByTitleOrContentLike(keywords,page,size);
        return new Result(true,StatusCode.OK,"检索文章成功",new PageResult<>(pageArticle.getTotalElements(),pageArticle.getContent()));
    }
}
