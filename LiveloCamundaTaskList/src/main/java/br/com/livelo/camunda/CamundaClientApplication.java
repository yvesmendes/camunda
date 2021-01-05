package br.com.livelo.camunda;

import org.camunda.bpm.extension.rest.EnableCamundaRestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCamundaRestClient
public class CamundaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaClientApplication.class, args);
	}

}
