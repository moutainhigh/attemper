package com.github.attemper.executor;

import com.github.attemper.common.constant.GlobalConstants;
import com.github.attemper.security.conf.SecurityConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import({
        SecurityConfiguration.class
})
@EnableDiscoveryClient
@EnableTransactionManagement
//@EnableJpaRepositories(GlobalConstants.jpaRepositoryLocation)   //jpa dao
//@EntityScan(GlobalConstants.jpaEntityLocation)   //jpa entity
@MapperScan(GlobalConstants.mybatisPlusMapperLocation)   //mybatis dao
@SpringBootApplication
public class ExecutorApplication {

    /**
     * spring boot app start entrance
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ExecutorApplication.class, args);
    }

}