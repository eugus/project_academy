package br.com.versao2.Academia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcademiaApplication {


	private static Logger log = LoggerFactory.getLogger(AcademiaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);

		log.info("Primeiro log");
	}

}
