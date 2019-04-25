package com.votacao.apivotacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.votacao.apivotacao", "controller", "service"})
@SpringBootApplication
public class ApivotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApivotacaoApplication.class, args);
	}

}
