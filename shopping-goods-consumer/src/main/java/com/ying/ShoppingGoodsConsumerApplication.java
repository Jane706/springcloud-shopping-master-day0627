package com.ying;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

@EnableDiscoveryClient
@EnableAsync//打开异步任务
@EnableScheduling    // 开启基于注解的定时任务支持
public class ShoppingGoodsConsumerApplication {
	public static void main(String[] args) {
		//        SpringApplication.r,un(ProductConfigApplication.class, args);
		ConfigurableApplicationContext run = SpringApplication.run(ShoppingGoodsConsumerApplication.class);
		Environment env = run.getEnvironment();
		String port = env.getProperty("server.port");
		String path = env.getProperty("server.servlet.context-path");
		System.out.println("\n--------------------------------------\n\t" +
				"Application is running! Access URLs:\n\t" +
				"Local: \t\thttp://localhost:" + port + path+ "/index.html\n\t" +
				"----------------------------------------------------------");
	}
}
