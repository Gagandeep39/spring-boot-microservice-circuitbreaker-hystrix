/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-04-21 00:59:41
 * @modify date 2020-04-21 00:59:41
 * @desc [description]
 */
package com.gagan.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class DemoApplication {

	/**
	 * Used to make REST calls from inside the code 
	 * Soon to be deprecated method 
	 * Successir is WebClient
	 * @LoadBalanced RestTemplate or WebClient bean to be configured to use a LoadBalancerClient.
	 * @return RestTemplate Object
	 * 
	 */
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		// return new RestTemplate();
		// Adding a time out using rest template
		HttpComponentsClientHttpRequestFactory factory  = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectTimeout(3000);
		return new RestTemplate(factory);
	}

	/**
	 * Ued to erfrom Http rquest
	 * @return SIngleton Webclient object
	 */
	@Bean
	public WebClient.Builder getWebClient(){
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
