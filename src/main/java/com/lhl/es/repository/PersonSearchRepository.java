package com.lhl.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * es dao操作类.
 */
public interface PersonSearchRepository extends ElasticsearchRepository<Person, Long> {
}
