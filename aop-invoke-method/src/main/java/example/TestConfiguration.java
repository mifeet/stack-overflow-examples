package example;

import example.aop.ReplacingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy // enables AOP support
@Configuration
public class TestConfiguration {
    @Bean
    public TestClass testClass() {
        return new TestClass();
    }

    @Bean
    public ReplacingAspect aspect() {
        return new ReplacingAspect();
    }
}
