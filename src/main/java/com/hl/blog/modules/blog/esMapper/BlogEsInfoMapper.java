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
     * 按照AddTime降序获取所有博客（分页）
     * @return
     */
    List<BlogEsInfo> getAllByOrderByAddTimeDesc(Pageable pageable);

    /**
     * 按照AddTime降序获取所有博客
     * @return
     */
    List<BlogEsInfo> getAllByOrderByAddTimeDesc();

    long count();

    /**
     * 获取最新推荐博客
     * @return
     */
    List<BlogEsInfo> findAllByIsRecommendTrueOrderByAddTimeDesc(Pageable pageable);

    /**
     * 类型
     * @return
     */
    List<BlogEsInfo> findAllByTypeEqualsOrderByAddTimeDesc(Pageable pageable, Integer type);

    /**
     * 获取类型总数
     * @return
     */
    long countAllByTypeEquals(Integer type);

    /**
     * 标签
     * @return
     */
    List<BlogEsInfo> findAllByTagsEqualsOrderByAddTimeDesc(Pageable pageable, Integer tags);

    /**
     * 标签
     * @return
     */
    long countAllByTagsEquals(Integer tags);
}
