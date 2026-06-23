package com.dgsw.fastserver.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Fast")
                                .description("선생님과 학생 사이 심부름 모집 및 지원을 쉽게 사용할 수 있는 서비스 'Fast'입니다.")
                                .version("v1.0.0")
                );
    }
}