package com;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "mutiplehotelopenapi", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(info=@Info(title = "Multiple Hotel API", version = "1.0",description = "Multiple Hotel Management System"))
public class MutipleHotelSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutipleHotelSystemApplication.class, args);

		System.out.println("Multiple Hotel Running");


	}



}
