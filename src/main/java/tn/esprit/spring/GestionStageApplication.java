package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDiscoveryClient

public class GestionStageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionStageApplication.class, args);
	}
}
