package com.crud.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.crud.demo.security.CustomLogoutSuccessHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

	@Configuration
	@EnableWebSecurity
	public class SecurityConfig extends WebSecurityConfigurerAdapter {
		  @Override
		    protected void configure(HttpSecurity http) throws Exception {
		        http
		            .authorizeRequests()
		                .antMatchers("/").permitAll()
		                .anyRequest().authenticated()
		                .and()
		            .formLogin()
		                .loginPage("/login")
		                .permitAll()
		                .and()
		            .oauth2Login()
		                .and()
		            .logout()
		                .logoutUrl("/logout")
		                .logoutSuccessUrl("/logout-success") // Redirect to /logout-success
		                .permitAll();
		    }
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	            .inMemoryAuthentication()
	            .passwordEncoder(passwordEncoder())
	            .withUser("user")
	            .password(passwordEncoder().encode("password"))
	            .roles("USER");
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public LogoutSuccessHandler customLogoutSuccessHandler() {
	        return new CustomLogoutSuccessHandler();
	    }
	}
