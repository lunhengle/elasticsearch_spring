package com.lhl.es.service.impl;


import com.lhl.es.repository.Person;
import com.lhl.es.repository.PersonSearchRepository;
import com.lhl.es.service.PersonService;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * person service 实现类.
 */
@Service("personService")
public class PersonServiceImpl implements PersonService {
    /**
     * person repository.
     */
    @Autowired
    private PersonSearchRepository personSearchRepository;

    /**
     * 保存person.
     *
     * @param person person 对象
     */
    public void save(Person person) {
        personSearchRepository.save(person);
    }

    /**
     * 批量保存person.
     *
     * @param personList person 对象列表
     */
    public void saveAll(List<Person> personList) {
        personSearchRepository.saveAll(personList);
    }

    /**
     * 更新person.
     *
     * @param person person 对象
     */
    public void update(Person person) {
        personSearchRepository.save(person);
    }

    /**
     * 删除person.
     *
     * @param id person id
     */
    public void delete(Long id) {
        personSearchRepository.deleteById(id);
    }

    /**
     * 查询 person .
     *
     * @param builder 查询条件
     * @return person 列表
     */
    public Iterable<Person> search(QueryStringQueryBuilder builder) {
        return personSearchRepository.search(builder);
    }

    /**
     * 分页查询.
     *
     * @param builder 查询条件
     * @return 列表
     */
    public Page<Person> searchPage(QueryStringQueryBuilder builder, Pageable pageable) {
        return personSearchRepository.search(builder, pageable);
    }

    /**
     * 查询 person 列表.
     *
     * @param searchQuery 查询条件
     * @return person
     */
    @Override
    public Iterable<Person> searchQuery(SearchQuery searchQuery) {
        return personSearchRepository.search(searchQuery);
    }
}
