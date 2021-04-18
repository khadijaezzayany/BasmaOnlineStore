package ma.youcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.youcode.shared.SpringApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class BasmaOnlineApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("khadija test");

		SpringApplication.run(BasmaOnlineApplication.class, args);

	}

	// For Crypting Password
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
}
