package com.lhl.es.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 测试类.
 */
@Document(indexName = "person_index", type = "person")
public class Person implements Serializable {
    /**
     * id
     */
    @Id
    private Long id;
    /**
     * 名称.
     */
    private String name;
    /**
     * 手机号.
     */
    private String telephone;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "id=" + id + " name=" + name + "  telephone=" + telephone;
    }
}
