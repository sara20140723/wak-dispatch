package com.wak.dispatch;

import com.wak.dispatch.config.datasource.EmbeddedTomcatConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(EmbeddedTomcatConfiguration.class)
public class WakDispatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(WakDispatchApplication.class, args);
	}
}
