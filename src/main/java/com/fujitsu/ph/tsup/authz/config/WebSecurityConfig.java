//==================================================================================================
// Project Name :  Training Sign Up Project
// System Name  : Training Sign Up Project
// Class Name   : WebSecurityConfig.java
//
// <<Modification History>>
// Version | Date       | Updated By            | Content
// --------+------------+-----------------------+---------------------------------------------------
// 1.0.0   | 2020/07/29 | WS) J.Macabudbud      | Initial Version
//==================================================================================================
package com.fujitsu.ph.tsup.authz.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import com.fujitsu.ph.auth.provider.FpiLdapAuthenticationProvider;

/**
 * <pre>
 * This class contains the configuration for LDAP login authentication and http of survey project
 * 	application. It extends the WebSecurityConfigurerAdapter class.
 * </pre>
 * 
 * @author WS) J.Macabudbud
 * @version 0.01
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.fujitsu.ph")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("#{servletContext.contextPath}")
	private String servletContextPath;

	/**
	 * <pre>
	 * Base class filter for login page
	 * </pre>
	 * 
	 * @author WS) J.Macabudbud
	 * @version 1.0.0
	 *
	 */
	class LoginPageFilter extends GenericFilterBean {
		private Logger logger = LoggerFactory.getLogger(LoginPageFilter.class);

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {

			logger.debug("Is Authenticated[{}][{}]", SecurityContextHolder.getContext().getAuthentication(),
					((HttpServletRequest) request).getRequestURI());

			if (SecurityContextHolder.getContext().getAuthentication() != null
					&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
					&& (((HttpServletRequest) request).getRequestURI().equals(servletContextPath + "/login"))
					|| ((HttpServletRequest) request).getRequestURI().equals(servletContextPath + "/")) {

				logger.debug("user is authenticated but trying to access login page, redirecting to /");
				((HttpServletResponse) response).sendRedirect(servletContextPath + "/dashboard");
			}
			chain.doFilter(request, response);
		}
	}

	@Autowired
	private FpiLdapAuthenticationProvider authenticationProvider;
	
	/**
	 * <pre>
	 * Setup http security config
	 * </pre>
	 * 
	 * @param http HttpSecurity Object
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new LoginPageFilter(), UsernamePasswordAuthenticationFilter.class);
		String[] resourcesList = { "/", "/login", "/css/**", "/images/**", "/js/**" };

		http.csrf()
			.and()
			.authorizeRequests()
				.antMatchers(resourcesList)
				.permitAll()
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
