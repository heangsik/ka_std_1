package kr.co.yhs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Slf4j
@Configuration
public class LoggingConfig {
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter(){
        CommonsRequestLoggingFilter c = new CommonsRequestLoggingFilter();
        c.setIncludeHeaders(true);
        c.setIncludeQueryString(true);
        c.setIncludePayload(true);
        c.setIncludeClientInfo(true);

        return c;
    }
}
