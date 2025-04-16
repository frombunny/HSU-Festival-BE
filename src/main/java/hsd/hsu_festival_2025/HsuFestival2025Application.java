package hsd.hsu_festival_2025;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HsuFestival2025Application {

	public static void main(String[] args) {
		SpringApplication.run(HsuFestival2025Application.class, args);
	}

}
