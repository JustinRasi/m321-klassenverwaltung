package ch.tbz.m321_klassenverwaltung.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 * This class defines the OpenAPI bean used to customize the API documentation.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Creates a custom OpenAPI bean with API information.
     *
     * @return an OpenAPI instance with custom information
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("M321 Klassenverwaltung API")
                        .version("1.0")
                        .description("API documentation for M321 Klassenverwaltung"));
    }
}