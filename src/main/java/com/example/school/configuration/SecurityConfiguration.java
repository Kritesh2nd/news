package com.example.school.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;
    // private RestAuthenticationEntryPoint authenticationEntryPoint;

    // @Autowired
    // public SecurityConfiguration(RestAuthenticationEntryPoint authenticationEntryPoint){
    //     this.authenticationEntryPoint = authenticationEntryPoint;
    // }

    @Bean
    @Order(2)
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	System.out.println("securityFilterChain");
        http.authorizeHttpRequests(authorize -> authorize
//                .requestMatchers("/category").authenticated()
//                .requestMatchers("/user/**").permitAll()
//                .anyRequest().authenticated())
              .requestMatchers("/article/**").authenticated()
              .requestMatchers("/user/**").permitAll()
              .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder encoder) throws Exception{
    	System.out.println("auth printing.... "+auth.toString());
    	auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//        auth.inMemoryAuthentication()
//        .withUser("user").password(encoder.encode("password")).roles("USER");
        // .withUser("admin").password(encoder.encode("admin")).roles("ADMIN");
    }

}
