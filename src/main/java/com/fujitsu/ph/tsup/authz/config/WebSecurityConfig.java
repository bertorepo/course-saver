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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.auth.provider.FpiLdapAuthenticationProvider;
import com.fujitsu.ph.tsup.authz.service.AuthorizationService;
import com.fujitsu.ph.tsup.common.domain.Employee;

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

	@Autowired
	private AuthorizationService authorizationService;
	
	private boolean REGISTER;
	/**
	 * <pre>
	 * Base class filter for login page
	 * </pre>
	 * 
	 * @author WS) J.Macabudbud
	 * @version 1.0.0
	 * Revision Date : 2021-02-15
	 * 
	 */
	class LoginPageFilter extends GenericFilterBean {
		private Logger logger = LoggerFactory.getLogger(LoginPageFilter.class);

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			
            /*
             * Author: k.sala
             * 
             * If user authenticated and tries to access register page, will redirect to dashboard
             * 
             */
            if (((HttpServletRequest) request).getRequestURI().equals(servletContextPath + "/register")) {
                FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal();

                Employee employee = authorizationService.findDetailsByUsername(user.getUserName());
                if (employee == null) {
                    REGISTER = true;
                } else {
                    logger.debug("user is authenticated but trying to access login page, redirecting to /");
                    ((HttpServletResponse) response).sendRedirect(servletContextPath + "/dashboard");
                }
            }

			logger.debug("Is Authenticated[{}][{}]", SecurityContextHolder.getContext().getAuthentication(),
					((HttpServletRequest) request).getRequestURI());

			if (SecurityContextHolder.getContext().getAuthentication() != null
					&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
					&& (((HttpServletRequest) request).getRequestURI().equals(servletContextPath + "/login"))
					|| ((HttpServletRequest) request).getRequestURI().equals(servletContextPath + "/")) {
				
				if(REGISTER == true) {
					REGISTER = false;
					SecurityContextHolder.getContext().setAuthentication(null);
					((HttpServletResponse) response).sendRedirect(servletContextPath + "/login");
					return;
				}

				logger.debug("user is authenticated but trying to access login page, redirecting to /");
				((HttpServletResponse) response).sendRedirect(servletContextPath + "/dashboard");
			}
			
            /*
             * Author: k.sala
             * 
             * If user is in Register page and tries to access dashboard page, will redirect to register
             * 
             */
            if (SecurityContextHolder.getContext().getAuthentication() != null
                    && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                    && (((HttpServletRequest) request).getRequestURI()
                            .equals(servletContextPath + "/dashboard"))) {

                if (REGISTER == true) {
                    logger.debug(
                            "user is in register screen but trying to access dashboard page, redirecting to /register");
                    ((HttpServletResponse) response).sendRedirect(servletContextPath + "/register");
                }
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
	
    /**
     * @author k.sala
     * @return TsupAuthenticationSuccessHandler()
     */
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new TsupAuthenticationSuccessHandler();
    }

	/**
	 *@author k.sala
	 *Revision Date: 2021-02-15
	 *
	 * Replace defaultSuccessUrl with successHandler to choose which page to load
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new LoginPageFilter(), UsernamePasswordAuthenticationFilter.class);
		String[] resourcesList = { "/", "/login", "/css/**", "/images/**", "/js/**"};

		http.csrf()
			.and()
			.authorizeRequests()
				.antMatchers(resourcesList)
				.permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.successHandler(myAuthenticationSuccessHandler())
				/* .defaultSuccessUrl("/dashboard", true) */
				.and()
			.sessionManagement()
				.invalidSessionUrl("/login")
				.and()
			.logout()
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.clearAuthentication(true)
				.logoutSuccessUrl("/login")
				.permitAll()
				.and()
			.csrf().disable().cors();;
	}
}
