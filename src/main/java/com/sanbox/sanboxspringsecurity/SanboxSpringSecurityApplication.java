package com.sanbox.sanboxspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Map;

@SpringBootApplication
public class SanboxSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanboxSpringSecurityApplication.class, args);
	}
// Basic auth
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception {
//		return http
//				.httpBasic().and()
//				.authorizeHttpRequests()
//				.anyRequest().authenticated().and()
//				.build();
//	}

	@Bean
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route()
				.GET("/api/v4/greetings", request -> {
					UserDetails userDetails = request.principal().map(Authentication.class::cast)
							.map(Authentication::getPrincipal)
							.map(UserDetails.class::cast)
							.orElseThrow();
					return ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(Map.of("greeting", "Hello, %s (V4)".formatted(userDetails.getUsername())));
				})
				.build();
	}

	//точки входа

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.authorizeHttpRequests()
//				.anyRequest().authenticated().and()
//				.build();
//	}

//	//переопределение точки входа
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.authorizeHttpRequests()
//				.anyRequest().authenticated().and()
//				.exceptionHandling()
//				.authenticationEntryPoint((request, response, authException) -> {
//					authException.printStackTrace();
//					response.setStatus(HttpStatus.UNAUTHORIZED.value());
//				}).and()
//				.build();
//	}

	//перенаправление пользователя
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http
//				.authorizeHttpRequests()
//				.requestMatchers("/public/**").permitAll()
//				.anyRequest().authenticated().and()
//				.exceptionHandling()
//				.authenticationEntryPoint((request, response, authException) -> {
//					response.sendRedirect("http://localhost:8080/public/403.html");
//				}).and()
//				.build();
//	}
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http
//				.csrf().disable()
//				.formLogin().and()
//				.authorizeHttpRequests()
//				.requestMatchers("/public/**").permitAll()
//				.anyRequest().authenticated().and()
//				.exceptionHandling()
//				.authenticationEntryPoint((request, response, authException) -> {
//					response.sendRedirect("http://localhost:8080/public/sign-in.html");
//				}).and()
//				.build();
//	}

	// Basic точка входа
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http
//				.httpBasic().and()
//				.authorizeHttpRequests()
//					.anyRequest().authenticated().and()
//				.build();
//	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new BasicAuthenticationEntryPoint();
//		basicAuthenticationEntryPoint.setRealmName("Realm");
//		return http
//				.httpBasic().authenticationEntryPoint((request, response, authException) -> {
//					authException.printStackTrace();
//					basicAuthenticationEntryPoint.commence(request, response, authException);
//				}).and()
//				.authorizeHttpRequests()
//					.anyRequest().authenticated().and()
//				.exceptionHandling()
//					.authenticationEntryPoint(basicAuthenticationEntryPoint)
//					.and()
//				.build();
//	}


}
