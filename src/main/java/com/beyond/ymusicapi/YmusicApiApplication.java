package com.beyond.ymusicapi;

import com.beyond.ymusicapi.request.common.Client;
import com.beyond.ymusicapi.request.common.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class YmusicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YmusicApiApplication.class, args);
	}

	@Bean
	public Client getClient() {
		return new Client();
	}

	@Bean
	public Context getContext() {
		return new Context(getClient());
	}

	@Bean
	public OkHttpClient getOkHttpClient() {
		return new OkHttpClient();
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

}
