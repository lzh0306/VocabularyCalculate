package cn.touale.ve.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Touale
 * @description Swagger3Config
 * @date 2022/6/11 12:45
 */
@EnableKnife4j
@Configuration
@EnableOpenApi
public class Swagger3Config {
    @Bean
    public Docket createRestApi() {
        //返回文档摘要信息
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 生成接口信息，包括标题、联系人等
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("词汇量估算系统接口文档")
                .description("如有疑问，可联系开发人员")
                .contact(new Contact("广州大学软件191", "", ""))
                .version("1.0")
                .build();
    }
}
