package com.MADG2;

import com.MADG2.security.entity.Rol;
import com.MADG2.security.enums.RolName;
import com.MADG2.security.service.RolService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class F1ntasyManagerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(F1ntasyManagerApplication.class, args);
	}

	@Bean  // (hace los NEW, inversion de control)
	public CommandLineRunner demoData(RolService rolService){

		return args -> {

			/*
			Rol admin = new Rol(RolName.ROL_ADMIN);
			Rol manager = new Rol(RolName.ROL_MANAGER);

			rolService.save(admin);
			rolService.save(manager);

			 */


		};

	}

}
