package com.itsc.patientManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PatientManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientManagementApplication.class, args);
	}

}
