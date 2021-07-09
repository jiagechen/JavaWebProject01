package cn.njust.label.config;

import cn.njust.label.common.config.BaseSwaggerConfig;
import cn.njust.label.common.domain.SwaggerProperties;
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
                .apiBasePackage("cn.njust.label.controller")
                .title("Lt后台系统")
                .description("Lt后台相关接口文档")
                .contactName("某")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
