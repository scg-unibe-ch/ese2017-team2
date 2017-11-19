package ch.unibe.eseteam2;

import java.util.Collections;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Autowired
	private UserDetailsManager userDetailsManager;

	@Bean
	InitializingBean createAdmin() {
		return () -> {
			if (!userDetailsManager.userExists("admin@anitrans.ch")) {
				User admin = new User("admin@anitrans.ch", "anitrans", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
				userDetailsManager.createUser(admin);
			}
		};
	}

}
