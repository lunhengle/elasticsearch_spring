package com.lhl.es;

import com.lhl.es.repository.Person;
import com.lhl.es.service.PersonService;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class TestPersonService {
    /**
     * 注入person service.
     */
    @Resource
    private PersonService personService;

    /**
     * 测试保存 person.
     */
    @Test
    public void savePerson() {
        Person person = new Person();
        person.setId(1L);
        person.setName("伦恒乐");
        person.setTelephone("15000000000");
        personService.save(person);
    }

    /**
     * 批量保存 person.
     */
    @Test
    public void saveAllPerson() {
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person();
        person1.setId(1L);
        person1.setName("张三丰");
        person1.setTelephone("15000000001");
        personList.add(person1);
        Person person2 = new Person();
        person2.setId(2L);
        person2.setName("张飞");
        person2.setTelephone("15000000002");
        personList.add(person2);
        Person person3 = new Person();
        person3.setId(3L);
        person3.setName("张学友");
        person3.setTelephone("15000000003");
        personList.add(person3);
        personService.saveAll(personList);
    }

    /**
     * 更新 person
     */
    @Test
    public void updatePerson() {
        Person person = new Person();
        person.setId(1L);
        person.setName("张飞");
        person.setTelephone("15000000000");
        personService.update(person);
    }

    /**
     * 删除 person.
     */
    @Test
    public void deletePerson() {
        personService.delete(1L);
    }

    /**
     * 得到 person.
     */
    @Test
    public void getPerson() {
        Iterable<Person> iterable = personService.search(QueryBuilders.queryStringQuery("张"));
        for (Person person : iterable) {
            System.out.println(person);
        }
    }

    /**
     * 分页查询.
     */
    @Test
    public void getPersonPage() {
        Page<Person> personPage = personService.searchPage(QueryBuilders.queryStringQuery("张"), PageRequest.of(0, 3, Sort.by(Sort.Order.asc("id"))));
        for (Person person : personPage) {
            System.out.println(person);
        }
    }

    /**
     * 复杂查询.
     */
    @Test
    public void getPersonSearchQuery() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("张"))
                .withFilter(QueryBuilders.queryStringQuery("友"))
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC))
                .build();
        Iterable<Person> iterable = personService.searchQuery(searchQuery);
        for (Person person : iterable) {
            System.out.println(person);
        }
    }
}
