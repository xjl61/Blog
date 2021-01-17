package com.littercarp.articleservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.littercarp.articleservice.entity.Article;
import com.littercarp.articleservice.entity.vo.ArticleQuery;
import com.littercarp.articleservice.service.ArticleService;
import com.littercarp.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * article 前端控制器
 * </p>
 *
 * @author litterCarp
 * @since 2021-01-17
 */
@Api("文章管理")
@RestController
@CrossOrigin //跨域
@RequestMapping("/articleservice/article")
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    @ApiOperation("所有文章列表")
    @GetMapping("getArticleInfo")
    public R list(){
        List<Article> list = articleService.list(null);
        return R.ok().data("list",list);
    }

    @ApiOperation("根据Id删除文章")
    @DeleteMapping("deleteArticle/{id}")
    public R removeById(@ApiParam(name = "id", value = "文章Id", required = true)  @PathVariable String id){
        articleService.removeById(id);
        return R.ok().message("删除文章成功！");
    }

    @ApiOperation("分页文章列表")
    @GetMapping("pageArticle/{current}/{limit}")
    public R pageList(@PathVariable long current,@PathVariable long limit){
        //创建page对象
        Page <Article> pageArticle = new Page<>(current,limit);
        articleService.page(pageArticle,null);
        long total = pageArticle.getTotal();//总记录数
        List<Article> records = pageArticle.getRecords();//list集合
        return R.ok().data("total", total).data("rocords", records);
    }

    @ApiOperation("多条件分页查询文章")
    @PostMapping("pageArticleCondition/{current}/{limit}")
    public R pageArticleCondition(@PathVariable long current, @PathVariable long limit, @RequestBody ArticleQuery articleQuery){
        // 创建page对象
        Page <Article> pageAeticleCondition = new Page<>(current,limit);

        // 构造条件
        QueryWrapper wrapper = new QueryWrapper();

        // 获取条件
        String title = articleQuery.getTitle();
        String begin = articleQuery.getBegin();
        String end = articleQuery.getEnd();
        if (!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("created_time",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("created_time",end);
        }
        wrapper.orderByDesc("created_time");

        // 调用service方法实现
        articleService.page(pageAeticleCondition,wrapper);
        long total = pageAeticleCondition.getTotal();
        List<Article> records = pageAeticleCondition.getRecords();
        return R.ok().data("total",total).data("records",records);
    }

    @ApiOperation("添加文章")
    @PostMapping("addArticle")
    public R save(@RequestBody Article article){
        articleService.save(article);
        return R.ok();
    }

    @ApiOperation("根据id查询文章")
    @GetMapping("getArticleById/{id}")
    public R getArticleById(@PathVariable String id){
        Article article = articleService.getById(id);
        return R.ok().data("item",article);
    }

    @ApiOperation("根据id修改文章信息")
    @PutMapping("updateArticle/{id}")
    public R updateArticle (@PathVariable String id,@RequestBody Article article){
        article.setId(id);
        articleService.updateById(article);
        return R.ok();
    }

}