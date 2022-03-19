package com.ufcg.es.healthtrack;

import com.ufcg.es.healthtrack.filter.JWTFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HealthtrackApplication {

	@Bean
	public FilterRegistrationBean<JWTFilter> jwtFilter() {
		FilterRegistrationBean<JWTFilter> jwtFilter = new FilterRegistrationBean<JWTFilter>();
		jwtFilter.setFilter(new JWTFilter());
		jwtFilter.addUrlPatterns("/auth/test",
								 "/exame/glicemia",
								 "/exame/glicemia/listar",
								 "/exame/pdf/upload",
								 "/exame/pdf/download/",
								 "/exame/fezes",
								 "/exame/fezes/listar",
				                 "/exame/hemograma",
								 "/exame/hemograma/listar",
								 "/exame/colesterol",
								 "/exame/colesterol/listar",
							  	 "/exame/pressao",
							  	 "/exame/pressao/listar");

		return jwtFilter;
	}

	public static void main(String[] args) {
		SpringApplication.run(HealthtrackApplication.class, args);
	}

}
