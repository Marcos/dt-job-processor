package io.datapath.web;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "io.datapath" })
public class Application {

	public static void main(String[] args) {
		run(Application.class, args);
	}
}
