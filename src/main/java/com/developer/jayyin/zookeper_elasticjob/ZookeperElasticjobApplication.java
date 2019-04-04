package com.developer.jayyin.zookeper_elasticjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;
import java.util.logging.Logger;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ZookeperElasticjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeperElasticjobApplication.class, args);

        System.out.println("Server is started...");
    }

}
