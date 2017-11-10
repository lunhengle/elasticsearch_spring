package com.lhl.es.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 产品.
 */
@Document(indexName = "product_index", type = "product")
public class Product {
    /**
     * id.
     */
    @Id
    private Long id;
    /**
     * 名称.
     */
    private String name;
    /**
     * 版本号.
     */
    private String version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "id =" + id + " name=" + name + " version=" + version;
    }
}
