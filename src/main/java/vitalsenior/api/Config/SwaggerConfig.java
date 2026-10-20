package vitalsenior.api.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI vitalSeniorApi() {
        return new OpenAPI().info(new Info()
                .title("VitalSênior API")
                .version("v1")
                .description("API de monitoramento de saúde"));
    }
}
