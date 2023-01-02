package com.hl.blog.modules.blog.esMapper;

import com.hl.blog.modules.blog.model.BlogEsInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BlogEsInfoMapper extends ElasticsearchRepository<BlogEsInfo, Integer> {
    /**
     * 根据内容查询博客
     * @param content
     * @return
     */
    List<BlogEsInfo> findByContent(String content);

    /**
     * 按照AddTime降序获取所有博客
     * @return
     */
    List<BlogEsInfo> getAllByOrderByAddTimeDesc(Pageable pageable);

    long count();

    /**
     * 获取最新推荐博客
     * @return
     */
    List<BlogEsInfo> findAllByIsRecommendTrueOrderByAddTimeDesc(Pageable pageable);
}
