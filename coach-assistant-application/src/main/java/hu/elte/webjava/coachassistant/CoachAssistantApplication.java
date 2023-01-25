package hu.elte.webjava.coachassistant;

import hu.elte.webjava.coachassistant.application.service.TestDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoachAssistantApplication {

	private final TestDataGenerator testDataGenerator;

	@Autowired
	public CoachAssistantApplication(TestDataGenerator testDataGenerator) {
		this.testDataGenerator = testDataGenerator;
	}

	public static void main(String[] args) {
		SpringApplication.run(CoachAssistantApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			testDataGenerator.createTestData();
		};
	}
}
