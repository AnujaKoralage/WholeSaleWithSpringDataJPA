package lk.ijse.dep;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan("lk")
@Import(JpaConfig.class)
@Configuration
public class AppConfig {
}
