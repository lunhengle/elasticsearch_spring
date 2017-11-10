package com.lhl.es.service;

import com.lhl.es.repository.Person;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

public interface PersonService {
    /**
     * 保存person.
     *
     * @param person person 对象
     */
    void save(Person person);

    /**
     * 批量保存person.
     *
     * @param personList person 列表
     */
    void saveAll(List<Person> personList);

    /**
     * 更新person.
     *
     * @param person person 对象
     */
    void update(Person person);

    /**
     * 删除person.
     *
     * @param id person id
     */
    void delete(Long id);

    /**
     * 查询person 列表.
     *
     * @param builder 查询条件
     * @return person 列表
     */
    Iterable<Person> search(QueryStringQueryBuilder builder);

    /**
     * 分页查询.
     *
     * @param builder 查询条件
     * @return 列表
     */
    Page<Person> searchPage(QueryStringQueryBuilder builder, Pageable pageable);

    /**
     * 查询 person 列表.
     *
     * @param searchQuery 查询条件
     * @return person
     */
    Iterable<Person> searchQuery(SearchQuery searchQuery);

}
