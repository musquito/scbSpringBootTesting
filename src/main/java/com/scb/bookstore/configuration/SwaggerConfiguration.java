package com.scb.bookstore.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket selectApi() {

        return new  Docket(DocumentationType.SWAGGER_2)
        	      .apiInfo(apiEndPointsInfo())
        	      .securityContexts(Arrays.asList(securityContext()))
        	      .securitySchemes(Arrays.asList(apiKey()))
        	      .select()
        	      .apis(RequestHandlerSelectors.any())
        	      .paths(PathSelectors.any())
        	      .build();
    }
    private SecurityContext securityContext() { 
        return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
    } 
    private ApiKey apiKey() { 
        return new ApiKey("JWT", "Authorization", "header"); 
    }
    private List<SecurityReference> defaultAuth() { 
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
        authorizationScopes[0] = authorizationScope; 
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
    }
    private ApiInfo apiEndPointsInfo() {

        return new ApiInfoBuilder().title("SCB Book Store API Test")
                .description("Created by Pansak Kangphukeaw")
                .version("0.0.1")
                .build();
    }
}
