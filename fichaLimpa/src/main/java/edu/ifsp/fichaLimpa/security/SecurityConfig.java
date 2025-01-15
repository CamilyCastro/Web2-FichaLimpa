package edu.ifsp.fichaLimpa.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.annotation.PostConstruct;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;

	@Bean
	UserDetailsService userDetailsService() {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	
	/* 
	 * Referências:
	 * SecurityFilterChain - https://docs.spring.io/spring-security/reference/servlet/architecture.html
	 * Login form - https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			/* -- begin: H2 Console -- */
			.headers(headers -> headers
					.frameOptions(frames -> frames
							.sameOrigin()
					)
			)
			.csrf(csrf -> csrf
					.ignoringRequestMatchers("/h2-console/**", "/api/**")
			)
			/* -- end: H2 Console -- */
			
			.authorizeHttpRequests(auth ->	auth
					.requestMatchers("/", "/home", "/css/**", "/images/**", "/js/**", "/login", "/api/**", 
									 "/cidadao/cadastro", "/politico/listar", "/politico/perfil/*", 
									 /*"/api/politico/ranking",*/ "/publicacao/perfil/*", 
									 "/comentario/perfil/*", "/h2-console", "/fragments/**").permitAll()
					.requestMatchers("/**").hasRole("USER")
			)		
			.formLogin(form -> form
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/", true)
			    .failureUrl("/login?error") 
			)
			 .logout()
		        .logoutSuccessUrl("/login?logout") // Redirecionamento após logout
		        .invalidateHttpSession(true) // Invalidar a sessão
		        .clearAuthentication(true) // Limpar autenticação
		        .permitAll();
			
		return http.build();
	}
	
}
