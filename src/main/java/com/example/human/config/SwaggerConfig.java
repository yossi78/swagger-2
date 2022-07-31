package com.example.human.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.ArrayList;
import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .host("localhost:8080")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example"))
            .paths(regex("/v1.*"))
            .build().apiInfo(getApiInfo());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }



    private ApiInfo getApiInfo() {
        return new ApiInfo(
            "Ares Enrichment Service",
            "Spring Boot REST API for Ares Enrichment Dervice",
            "1.0",
            "Terms of service",
            new Contact("Yossi Tal", "https://springframework.guru/about/", "yossi_tal@intuit.com"),
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<VendorExtension>());
    }
}

