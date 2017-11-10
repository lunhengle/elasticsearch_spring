package com.lhl.es.service;

import com.lhl.es.repository.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * 产品service.
 */
public interface ProductService {
    /**
     * 添加 产品.
     *
     * @param product 产品
     */
    void add(Product product);

    /**
     * 更新 产品.
     *
     * @param product 产品
     */
    void update(Product product);

    /**
     * 根据id得到产品.
     *
     * @param searchQuery 查询条件
     * @return 产品
     */
    List<Product> get(SearchQuery searchQuery);

    /**
     * 删除 产品.
     *
     * @param id id
     */
    void delete(Long id);

    /**
     * 批量增加 产品.
     *
     * @param list 产品
     */
    void addList(List<Product> list);

    /**
     * 分页查询.
     *
     * @param searchQuery 查询条件
     * @return 产品列表
     */
    public Page<Product> getPage(SearchQuery searchQuery);
}
