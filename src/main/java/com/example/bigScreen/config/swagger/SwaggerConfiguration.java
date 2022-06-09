package com.example.bigScreen.config.swagger;


import com.example.bigScreen.BigScreenApplication;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: SuKai
 * @date: 2022/3/30
 * @time: 10:35
 * @description: swagger配置类
 */
@ConditionalOnWebApplication
@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements ApiListingScannerPlugin {

    /**
     * Swagger2的配置文件，这里可以配置Swagger2的一些基本的内容，比如扫描的包等等
     */
    @Bean
    public Docket createRestApi() {

        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder token = new ParameterBuilder();
        token.name("Authorization").description("Authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        pars.add(token.build());

        ParameterBuilder languageCode = new ParameterBuilder();
        languageCode.name("languageCode").description("languageCode")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        pars.add(languageCode.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BigScreenApplication.class.getPackage().getName()))
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());

    }

    /**
     * 构建API文档的详细信息函数
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目接口 API")
                .version("1.0")
                .build();
    }


    /**
     * Security指定的登录接口不会出现在swagger中，为了方便可以自定义登录接口在swagger中。
     */
    @Override
    public List<ApiDescription> apply(DocumentationContext documentationContext) {
        return new ArrayList<ApiDescription>(
                Arrays.asList(
                        new ApiDescription(
                                "/user/login",
                                "登录",
                                Arrays.asList(
                                        new OperationBuilder(
                                                new CachingOperationNameGenerator())
                                                .method(HttpMethod.POST)//http请求类型
                                                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                                                .summary("登录")
                                                .notes("登录")//方法描述
                                                .tags(Sets.newHashSet("登录"))//归类标签
                                                .parameters(
                                                        Arrays.asList(
                                                                new ParameterBuilder()
                                                                        .description("用户名")
                                                                        .type(new TypeResolver().resolve(String.class))
                                                                        .name("username")
                                                                        .parameterType("query")
                                                                        .parameterAccess("access")
                                                                        .required(true)
                                                                        .modelRef(new ModelRef("string")) //<5>
                                                                        .build(),
                                                                new ParameterBuilder()
                                                                        .description("密码")
                                                                        .type(new TypeResolver().resolve(String.class))
                                                                        .name("password")
                                                                        .parameterType("query")
                                                                        .parameterAccess("access")
                                                                        .required(true)
                                                                        .modelRef(new ModelRef("string")) //<5>
                                                                        .build()
                                                        )
                                                ).build()),
                                false)
                )
        );
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }
}
