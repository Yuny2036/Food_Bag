package dr.foody.config;


import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.cors.CorsConfiguration;
        import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
        import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("127.0.0.1"); // 모든 origin 허용, 실제 운영 환경에서는 필요에 따라 수정
        config.addAllowedMethod("127.0.0.1");
        config.addAllowedHeader("127.0.0.1");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
