package com.jdc.onestop.balance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource("classpath:/security-token.properties")
public class BalanceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalanceApiApplication.class, args);
	}

}
