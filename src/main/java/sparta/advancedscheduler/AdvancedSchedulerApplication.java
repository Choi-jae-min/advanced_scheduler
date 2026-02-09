package sparta.advancedscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AdvancedSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedSchedulerApplication.class, args);
    }

}
