
package enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewsApplication {

    public static void main(String[] args) {

        // link to resource: http://localhost:8080/index
        SpringApplication.run(NewsApplication.class, args);
    }
}
