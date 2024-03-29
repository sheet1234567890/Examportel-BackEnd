package com.adda.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.adda.app.ServiceImpl.UserDetailsServiceImpl;
@EnableWebSecurity
@SuppressWarnings("deprecation")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private BCryptPasswordEncoder encode;
	@Autowired
	private  jwtAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private jwtAuthenticationFilter jwtauthenticationfilter;
	@Bean
	
	public AuthenticationManager authenticationmanagerBean() throws Exception 
	{
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encode);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
		   .csrf()
		   .disable()
		   .cors()
		   .disable()
		   .authorizeRequests()
		   .antMatchers("/generate-token","/user/").permitAll()
		    .antMatchers(HttpMethod.OPTIONS).permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
		    .and()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		  http.addFilterBefore(jwtauthenticationfilter, UsernamePasswordAuthenticationFilter.class);
	}
}
