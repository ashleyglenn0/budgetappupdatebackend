package com.budgetupdate.budgetupdate;

import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = OpenAiAutoConfiguration.class)
public class BudgetupdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetupdateApplication.class, args);
	}

}
