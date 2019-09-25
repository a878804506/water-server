package com.cyh.water;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WaterServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterServerApplication.class, args);
	}

}
