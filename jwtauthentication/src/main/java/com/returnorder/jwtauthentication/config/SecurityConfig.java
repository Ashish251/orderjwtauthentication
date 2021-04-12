package com.returnorder.jwtauthentication.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

import com.returnorder.jwtauthentication.loader.UserDataLoader;
import com.returnorder.jwtauthentication.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private Logger log = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// super.configure(auth);
		log.info("SecurityConfig :: configure :: AuthenticationManagerBuilder");
		auth.userDetailsService(customUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http);
		log.info("SecurityConfig :: configure :: HttpSecurity");
		String str = "http://login-service/login";
		
		http.csrf().disable().cors().disable().authorizeRequests().antMatchers("/token").permitAll().anyRequest()
				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling().authenticationEntryPoint(entryPoint).and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

		//http.logout(logout -> logout.logoutSuccessUrl("/login"));
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		log.info("SecurityConfig :: passwordEncoder");
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		log.info("SecurityConfig :: authenticationManagerBean");
		return super.authenticationManagerBean();
	}

}
