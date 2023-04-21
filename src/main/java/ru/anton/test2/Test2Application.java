package ru.anton.test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.anton.test2.Facade.SQL;
import ru.anton.test2.Facade.TestConn;

@SpringBootApplication
public class Test2Application {

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(Test2Application.class, args);
		SQL.openConnection();
	}

}
