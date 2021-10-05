package com.example;

import com.example.model.service.PersonService;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OpenAPIDefinition(
    info = @Info(
            title = "demo-swagger-data",
            version = "0.0",
            description = "Api de Teste",
            license = @License(name = "MIT")
    )
)
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        ApplicationContext context = Micronaut.run(Application.class, args);
        PersonService service = context.getBean(PersonService.class);
        LOG.info(service.sayHi());
    }
}
