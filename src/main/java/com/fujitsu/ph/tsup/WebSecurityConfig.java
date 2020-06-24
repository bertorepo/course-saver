package com.fujitsu.ph.tsup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.fujitsu.ph.auth.provider.FpiLdapAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.fujitsu.ph")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private FpiLdapAuthenticationProvider authenticationProvider;
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] resourcesList = {
				"/", 
				"/login", 
				"/css/**",
				"/images/**",
				"/js/**" 
		};
			
		http
			.csrf()
			.and() 
			.authorizeRequests()
				.antMatchers(resourcesList).permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/dashboard", true)
				.and()
			.sessionManagement()
				.invalidSessionUrl("/login")
				.and()
			.logout()
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.clearAuthentication(true)
				.logoutSuccessUrl("/login") 
				.permitAll();
	}
}
