package com.teluguskillhub.springsecurity;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	//We gave @Bean annotation to above method, so we can use it with @Autowired annotation anywhere.


	public static void main(String[] args) {
//		System.out.println(ProcessHandle.current().pid());
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
