package pedromaia.dev.myfinances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MyFinancesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFinancesApplication.class, args);
    }

}
