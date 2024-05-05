package com.xu.blog.common.config;

import com.xu.blog.common.util.GsonUtil;
import com.xu.blog.entity.mysql.MysqlBlog;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Assigned;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author 11582
 */
public class SimpleUuidGenerator implements IdentifierGenerator {

    private static final Logger logger = LoggerFactory.getLogger(SimpleUuidGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {

        try {
            MysqlBlog mysqlBlog = GsonUtil.fromJson(GsonUtil.toJsonString(o), MysqlBlog.class);
            if (mysqlBlog.getId() != null) {
                Integer id = mysqlBlog.getId();
                if (id > 0) {
                    return id;
                }
            }
        } catch (Exception e) {
            logger.error("Error occured when get or set id value.", e);
        }

        return new Assigned().generate(sharedSessionContractImplementor, o);
    }
}

