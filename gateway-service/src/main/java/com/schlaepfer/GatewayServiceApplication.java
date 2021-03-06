package com.schlaepfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.schlaepfer.prefilters.SimpleLoggingPreFilter;

@SpringBootApplication
@EnableZuulProxy // Acts as reverse proxy, forwarding requests to other services
					// based on routes.
public class GatewayServiceApplication {

	@Bean
	public SimpleLoggingPreFilter simplePreFilter() {
		return new SimpleLoggingPreFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

}
