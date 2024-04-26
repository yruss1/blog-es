package com.xu.blog.repository;

import com.xu.blog.entity.es.EsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 11582
 */
@Repository
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {
}
