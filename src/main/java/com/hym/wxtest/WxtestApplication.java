package com.hym.wxtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
// 开启redis缓存
@EnableCaching
public class WxtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxtestApplication.class, args);
	}
}
