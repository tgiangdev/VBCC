package com.bgddt.qlvb.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.api.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Contact Application API")
                        .description(
                        "This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                        .version("1.0.0")
                );
    }
    @Bean
    public OpenApiCustomiser operationIdCustomiser() {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> {
                    String tag = operation.getTags().get(0);
                    if (isEqualsOperation(operation.getOperationId(), "findAllByPagination")) {
                        operation.setOperationId("findAll" + tag + "ByPagination");
                    } else if(isEqualsOperation(operation.getOperationId(), "findAll")) {
                        operation.setOperationId("findAll" + tag);
                    } else if(isEqualsOperation(operation.getOperationId(), "findById")) {
                        operation.setOperationId("find" + tag + "ById");
                    } else if(isEqualsOperation(operation.getOperationId(), "create")) {
                        operation.setOperationId("create" + tag);
                    } else if(isEqualsOperation(operation.getOperationId(), "update")) {
                        operation.setOperationId("update" + tag);
                    } else if(isEqualsOperation(operation.getOperationId(), "delete")) {
                        operation.setOperationId("delete" + tag);
                    }
                });
    }

    private boolean isEqualsOperation(String operationId, String funcName) {
        return (operationId.equals(funcName) || operationId.startsWith(funcName + "_"));
    }
}
