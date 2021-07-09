package cn.njust.label.demo.config;

import cn.njust.label.common.config.BaseSwaggerConfig;
import cn.njust.label.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger相关配置
 * Created by macro on 2019/4/8.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("cn.njust.label.demo.controller")
                .title("lt-demo系统")
                .description("SpringBoot版本中的一些示例")
                .contactName("mou")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }

}
