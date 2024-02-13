package book.my.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookMyCarServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyCarServiceRegistryApplication.class, args);
	}

}
