package com.xu.blog.service.Impl;

import com.xu.blog.entity.dto.BlogDto;
import com.xu.blog.entity.dto.CommentDto;
import com.xu.blog.entity.es.EsBlog;
import com.xu.blog.entity.mysql.Comment;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.entity.vo.BlogVo;
import com.xu.blog.mapper.EsResultMapper;
import com.xu.blog.repository.CommentRepository;
import com.xu.blog.repository.MysqlBlogRepository;
import com.xu.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 11582
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    private final MysqlBlogRepository mysqlBlogRepository;
    private final CommentRepository commentRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final EsResultMapper esResultMapper;
    public BlogServiceImpl(MysqlBlogRepository mysqlBlogRepository, CommentRepository commentRepository, ElasticsearchTemplate elasticsearchTemplate, EsResultMapper esResultMapper) {
        this.mysqlBlogRepository = mysqlBlogRepository;
        this.commentRepository = commentRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.esResultMapper = esResultMapper;
    }

    @Override
    public void addBlog(BlogVo vo) {
        mysqlBlogRepository.save(new MysqlBlog(vo));
    }

    @Override
    public List<MysqlBlog> selectAll() {
        return mysqlBlogRepository.queryAll();
    }

    @Override
    public Map<String, Object> search(String keyword, int pageNum, int pageSize) {
        Map<String, Object> map = new LinkedHashMap<>();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (StringUtils.isNotEmpty(keyword)){
            builder.should(QueryBuilders.matchPhraseQuery("title", keyword));
            builder.should(QueryBuilders.matchPhraseQuery("content", keyword));
        }
        HighlightBuilder.Field contentField = new HighlightBuilder.Field("content").preTags("<font style='color:red'>").postTags("</font>");
        HighlightBuilder.Field titleField = new HighlightBuilder.Field("title").preTags("<font style='color:red'>").postTags("</font>");
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(pageable)
                .withHighlightFields(titleField, contentField)
                .build();
        AggregatedPage<EsBlog> esBlogs = elasticsearchTemplate.queryForPage(query, EsBlog.class, esResultMapper);
        List<EsBlog> content = esBlogs.getContent();
        log.info("{}", content);
        map.put("total",esBlogs.getTotalElements());
        map.put("totalPages",esBlogs.getTotalPages());
        map.put("list", content);
        return map;
    }

    @Override
    public BlogDto selectById(String id) {
        Optional<MysqlBlog> byId = mysqlBlogRepository.findById(id);
        if (byId.isPresent()){
            BlogDto blogDto = new BlogDto();
            MysqlBlog mysqlBlog = byId.get();
            List<Comment> commentList = commentRepository.findCommentByBlogId(mysqlBlog.getId());

            List<CommentDto> comments = new ArrayList<>();
            if (!CollectionUtils.isEmpty(commentList)){
                for (Comment comment : commentList){
                    CommentDto commentDto = new CommentDto();
                    commentDto.setPid(comment.getParentId());
                    commentDto.setId(comment.getId());
                    commentDto.setMessage(comment.getMessage());
                    commentDto.setUserId(comment.getUserId());
                    commentDto.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comment.getTime()));
                    comments.add(commentDto);
                }
                Map<Integer, List<CommentDto>> map = comments
                        .stream()
                        .collect(Collectors.groupingBy(CommentDto::getPid));
                comments.forEach(commentDto -> {
                    commentDto.setCommentChildList(map.get(commentDto.getId()));
                    if (CollectionUtils.isEmpty(commentDto.getCommentChildList())){
                        commentDto.setCommentChildList(new ArrayList<>());
                    }
                });
                comments = comments.stream().filter(v -> v.getPid() == 0).collect(Collectors.toList());
            }

            blogDto.setAuthor(mysqlBlog.getAuthor());
            blogDto.setComments(comments);
            blogDto.setContent(mysqlBlog.getContent());
            blogDto.setCreateTime(mysqlBlog.getCreateTime());
            blogDto.setSummary(mysqlBlog.getSummary());
            blogDto.setDigCount(mysqlBlog.getDigCount());
            blogDto.setViewCount(mysqlBlog.getViewCount());
            blogDto.setUpdateTime(mysqlBlog.getUpdateTime());
            blogDto.setTitle(mysqlBlog.getTitle());

            return blogDto;
        }else {
            return null;
        }

    }
}
