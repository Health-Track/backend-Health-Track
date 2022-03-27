package com.ufcg.es.healthtrack.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Value("${frontend.base-url.cross-origin}")
  private String crossOrigin;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins(crossOrigin)
            .allowedMethods("PUT", "DELETE", "GET", "POST")
            .allowedHeaders("Content-Type", "Authorization");
  }

}
