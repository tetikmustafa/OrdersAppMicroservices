package com.smartera.apigateway;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0") )
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("orderservice", r -> r.path("/orders/**")
                        .and().method(HttpMethod.GET).uri("lb://ORDERSERVICE"))
                .route("productservice", r -> r.path("/products/**")
                        .and().method(HttpMethod.GET).uri("lb://PRODUCTSERVICE"))
                .route("customerservice", r -> r.path("/customers/**")
                        .and().method(HttpMethod.GET).uri("lb://CUSTOMERSERVICE"))
                .build();
    }
}
