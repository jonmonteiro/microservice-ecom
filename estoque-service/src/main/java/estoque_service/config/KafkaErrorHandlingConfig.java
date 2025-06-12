package estoque_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaErrorHandlingConfig {
    
    @Bean
    public DefaultErrorHandler errorHandler() {
        return new DefaultErrorHandler(
            (record, exception) -> {
                System.err.println("Failed to process: " + exception.getMessage());
            },
            new FixedBackOff(1000L, 3) 
        );
    }
}
