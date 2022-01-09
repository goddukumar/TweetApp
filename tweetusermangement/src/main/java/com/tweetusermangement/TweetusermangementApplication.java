package com.tweetusermangement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TweetusermangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetusermangementApplication.class, args);
	}

}
