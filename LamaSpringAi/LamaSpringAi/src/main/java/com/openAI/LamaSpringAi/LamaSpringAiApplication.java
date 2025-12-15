package com.openAI.LamaSpringAi;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import javax.net.ssl.SSLContext;

@SpringBootApplication
public class LamaSpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LamaSpringAiApplication.class, args);
	}



}
