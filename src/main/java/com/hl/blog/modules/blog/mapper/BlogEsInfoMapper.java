package com.hl.blog.modules.blog.mapper;

import com.hl.blog.modules.blog.model.BlogEsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogEsInfoMapper extends ElasticsearchRepository<BlogEsInfo, Integer> {
}
