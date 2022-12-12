package com.hl.blog.common.aop;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

/**
 * <p>
 * 数据库字段唯一值
 * </p>
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TableDataUnique.TableDataUniqueConstraintValidator.class)
public @interface TableDataUnique {

    // 表名
    String table();

    // 字段
    String column();

    String message() default "参数错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class TableDataUniqueConstraintValidator implements ConstraintValidator<TableDataUnique, String>, ApplicationContextAware {


        String table;
        String column;

        static ApplicationContext application;

        @Override
        @SuppressWarnings("all")
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            application = applicationContext;
        }

        @Override
        public void initialize(TableDataUnique tableDataUnique) {
            table = tableDataUnique.table();
            column = tableDataUnique.column();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (StrUtil.isEmpty(table) || StrUtil.isEmpty(column)) {
                return false;
            }

            String solitary = "'";
            String sql = "select count(1) 'count' from " + table + " where " + column + " = " + solitary + value + solitary;

            JdbcTemplate jdbcTemplate = application.getBean("jdbcTemplate", JdbcTemplate.class);
            Map<String, Object> map = jdbcTemplate.queryForMap(sql);

            return map.get("count").equals(0L);
        }

    }

}

