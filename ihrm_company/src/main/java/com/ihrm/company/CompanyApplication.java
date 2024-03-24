package com.ihrm.company;

import com.ihrm.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

/**
 * ClassName: CompanyApplication
 * Package: com.ihrm.company
 * Description:
 *
 * @Author R
 * @Create 2024/3/21 23:24
 * @Version 1.0
 */
//配置springboot包扫描
@SpringBootApplication(scanBasePackages = "com.ihrm")
//配置jpa包扫描
@EntityScan(value = "com.ihrm.domain.company")
public class CompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class,args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
