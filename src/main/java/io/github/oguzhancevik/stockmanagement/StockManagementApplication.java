package io.github.oguzhancevik.stockmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
@EnableSwagger2
public class StockManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockManagementApplication.class, args);
    }

}
