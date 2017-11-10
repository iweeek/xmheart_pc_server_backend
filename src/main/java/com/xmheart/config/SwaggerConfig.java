package com.xmheart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* Swagger配置信息.
* 
* @author x1ny
*/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final String TITLE = "厦门大学附属心血管病医院（厦门市心脏中心）官网接口文档";
	private static final String DESCRIPTION = "厦门大学附属心血管病医院（厦门市心脏中心）接口文档";
	
	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(buildApiInf())
				.select()
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo buildApiInf() {
		return new ApiInfoBuilder()
				.title(TITLE)
				.description(DESCRIPTION)
				.build();

	}
}
