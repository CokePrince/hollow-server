package com.hollow.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAsync
@SpringBootApplication
@MapperScan(basePackages = {"com.hollow.server.mapper"})
@ServletComponentScan(basePackages ={"com.hollow.server.testservice"})
public class HollowServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HollowServerApplication.class, args);
	}

}
