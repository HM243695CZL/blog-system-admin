package com.hl.blog.modules.blog.mapper;

import com.hl.blog.modules.blog.model.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogMapper extends ElasticsearchRepository<Blog, Long> {
}
