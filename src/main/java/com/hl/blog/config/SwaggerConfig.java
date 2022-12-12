package com.hl.blog.config;

import com.hl.blog.common.config.BaseSwaggerConfig;
import com.hl.blog.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by macro on 2018/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.hl.blog.modules")
                .title("blog博客管理")
                .description("blog博客管理")
                .contactName("blog")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
