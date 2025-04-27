package hsd.hsu_festival_2025;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HsuFestival2025Application {

	public static void main(String[] args) {
		SpringApplication.run(HsuFestival2025Application.class, args);
	}

	@Bean
	public CommandLineRunner dbConnectionCheck(
			@Value("${spring.datasource.url}") String dbUrl,
			@Value("${spring.datasource.username}") String dbUsername
	) {
		return args -> {
			System.out.println("=== Database Connection Info ===");
			System.out.println("URL: " + dbUrl);
			System.out.println("Username: " + dbUsername);
			System.out.println("================================");
		};
	}

}
