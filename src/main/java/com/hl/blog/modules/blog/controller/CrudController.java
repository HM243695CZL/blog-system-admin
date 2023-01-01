package com.hl.blog.modules.blog.controller;
 
import com.hl.blog.modules.blog.mapper.BlogMapper;
import com.hl.blog.modules.blog.model.Blog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
@Api(tags = "增删改查（文档）")
@RestController
@RequestMapping("crud")
public class CrudController {
    @Autowired
    private BlogMapper blogRepository;
 
    @ApiOperation("添加单个文档")
    @PostMapping("addDocument")
    public Blog addDocument() {
        Long id = 1L;
        Blog blog = new Blog();
        blog.setBlogId(id);
        blog.setTitle("Spring Data ElasticSearch学习教程" + id);
        blog.setContent("这是添加单个文档的实例" + id);
        blog.setAuthor("Tony");
        blog.setCategory("ElasticSearch");
        blog.setCreateTime(new Date());
        blog.setStatus(1);
        blog.setSerialNum(id.toString());
 
        return blogRepository.save(blog);
    }
 
    @ApiOperation("添加多个文档")
    @PostMapping("addDocuments")
    public Object addDocuments(Integer count) {
        List<Blog> blogs = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Long id = (long)i;
            Blog blog = new Blog();
            blog.setBlogId(id);
            blog.setTitle("Spring Data ElasticSearch学习教程" + id);
            blog.setContent("这是添加单个文档的实例" + id);
            blog.setAuthor("Tony");
            blog.setCategory("ElasticSearch");
            blog.setCreateTime(new Date());
            blog.setStatus(1);
            blog.setSerialNum(id.toString());
            blogs.add(blog);
        }
 
        return blogRepository.saveAll(blogs);
    }
 
    /**
     * 跟新增是同一个方法。若id已存在，则修改。
     * 无法只修改某个字段，只能覆盖所有字段。若某个字段没有值，则会写入null。
     * @return 成功写入的数据
     */
    @ApiOperation("修改单个文档")
    @PostMapping("editDocument")
    public Blog editDocument() {
        Long id = 1L;
        Blog blog = new Blog();
        blog.setBlogId(id);
        blog.setTitle("Spring Data ElasticSearch学习教程" + id);
        blog.setContent("这是修改单个文档的实例" + id);
        // blog.setAuthor("Tony");
        // blog.setCategory("ElasticSearch");
        // blog.setCreateTime(new Date());
        // blog.setStatus(1);
        // blog.setSerialNum(id.toString());
 
        return blogRepository.save(blog);
    }
 
    @ApiOperation("查找单个文档")
    @GetMapping("findById")
    public Blog findById(@Param("id") Long id) {
        return blogRepository.findById(id).get();
    }
 
    @ApiOperation("删除单个文档")
    @PostMapping("deleteDocument")
    public String deleteDocument(Long id) {
        blogRepository.deleteById(id);
        return "success";
    }
 
    @ApiOperation("删除所有文档")
    @PostMapping("deleteDocumentAll")
    public String deleteDocumentAll() {
        blogRepository.deleteAll();
        return "success";
    }
}