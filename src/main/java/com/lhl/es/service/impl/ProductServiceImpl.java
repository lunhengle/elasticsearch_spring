package com.lhl.es.service.impl;

import com.lhl.es.repository.Product;
import com.lhl.es.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品service 实现类.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    /**
     * 注入 es 操作模板.
     */
    private final ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public ProductServiceImpl(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    /**
     * 添加 产品.
     *
     * @param product 产品
     */
    @Override
    public void add(Product product) {
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId(String.valueOf(product.getId()));
        indexQuery.setObject(product);
        indexQuery.setIndexName("product_index");
        indexQuery.setType("product");
        elasticsearchTemplate.index(indexQuery);
    }

    /**
     * 更新 产品.
     *
     * @param product 产品
     */
    public void update(Product product) {
        this.add(product);
    }

    /**
     * 根据id得到产品.
     *
     * @param searchQuery 查询条件
     * @return 产品
     */
    public List<Product> get(SearchQuery searchQuery) {
        return elasticsearchTemplate.queryForList(searchQuery, Product.class);
    }

    /**
     * 删除 产品.
     *
     * @param id id
     */
    public void delete(Long id) {
        elasticsearchTemplate.delete(Product.class, String.valueOf(id));
    }

    /**
     * 批量增加 产品.
     *
     * @param list 产品
     */
    public void addList(List<Product> list) {
        List<IndexQuery> queries = new ArrayList<>();
        for (Product product : list) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(product.getId())).withIndexName("product_index").withType("product").withObject(product).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
    }

    /**
     * 分页查询.
     *
     * @param searchQuery 查询条件
     * @return 产品列表
     */
    public Page<Product> getPage(SearchQuery searchQuery) {
        return elasticsearchTemplate.queryForPage(searchQuery, Product.class);
    }
}
