package works.weave.socks.shipping.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ResourceMappings;
import org.springframework.data.rest.webmvc.support.JpaHelper;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import works.weave.socks.shipping.middleware.HTTPMonitoringInterceptor;

@Configuration
public class WebMvcConfig {
    @Bean
    public HTTPMonitoringInterceptor httpMonitoringInterceptor(
            ResourceMappings mappings,
            JpaHelper jpaHelper,
            RepositoryRestConfiguration repositoryConfiguration,
            ApplicationContext applicationContext,
            RequestMappingHandlerMapping requestMappingHandlerMapping) {

        return new HTTPMonitoringInterceptor(
                mappings,
                jpaHelper,
                repositoryConfiguration,
                applicationContext,
                requestMappingHandlerMapping
        );
    }

    @Bean
    public MappedInterceptor myMappedInterceptor(HTTPMonitoringInterceptor interceptor) {
        return new MappedInterceptor(new String[]{"/**"}, interceptor);
    }
}
