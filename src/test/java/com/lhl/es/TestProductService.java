package com.lhl.es;

import com.lhl.es.repository.Product;
import com.lhl.es.service.ProductService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试产品类.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestProductService {
    /**
     * 产品service.
     */
    @Resource
    private ProductService productService;

    /**
     * 测试产品.
     */
    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("苹果");
        product.setVersion("1.0-Version");
        productService.add(product);
    }

    /**
     * 批量增加产品.
     */
    @Test
    public void testAddList() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("华为");
        product1.setVersion("1.0-Version");
        products.add(product1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("苹果");
        product2.setVersion("1.0-Version");
        products.add(product2);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("小米");
        product3.setVersion("1.0-Version");
        products.add(product3);

        productService.addList(products);

    }

    /**
     * 更新产品.
     */
    @Test
    public void testUpdate() {
        Product product = new Product();
        product.setId(1L);
        product.setName("华为");
        product.setVersion("1.0-Version");
        productService.update(product);
    }

    /**
     * 删除产品.
     */
    @Test
    public void testDelete() {
        productService.delete(1L);
    }

    /**
     * 得到产品.
     */
    @Test
    public void getProduct() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("1.0-Version")).withFilter(QueryBuilders.queryStringQuery("华为")).withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC)).build();
        List<Product> productList = productService.get(searchQuery);
        if (null != productList) {
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }

    /**
     * 分页查询.
     */
    @Test
    public void getProductPage() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("1.0-Version"))
                .withPageable(PageRequest.of(0, 2, Sort.by(Sort.Order.desc("id")))).build();
        Page<Product> products = productService.getPage(searchQuery);
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
