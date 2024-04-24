package com.xu.blog.config;

import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.util.GsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class SimpleUuidGenerator implements IdentifierGenerator {

    private static final Logger logger = LoggerFactory.getLogger(SimpleUuidGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        //如果主键有值，则用实体自带的主键值;否则使用guid生成策略
        try {
            MysqlBlog mysqlBlog = GsonUtil.fromJson(GsonUtil.toJsonString(o), MysqlBlog.class);
            if (mysqlBlog.getId() != null) {
                String idStringValue = mysqlBlog.getId();
                if (StringUtils.isNotBlank(idStringValue)) {
                    return idStringValue;
                }
            }
        } catch (Exception e) {
            logger.error("Error occured when get or set id value.", e);
        }

        return null;
    }
}

