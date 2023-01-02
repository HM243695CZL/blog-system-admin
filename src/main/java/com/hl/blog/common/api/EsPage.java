package com.hl.blog.common.api;

import lombok.Data;

import java.util.List;

/**
 * elasticsearch分页
 * @param <T>
 */
@Data
public class EsPage<T> {
    private List<T> list;
    private Long total;
}
