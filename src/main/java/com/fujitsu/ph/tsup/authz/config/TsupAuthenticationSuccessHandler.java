package com.fujitsu.ph.tsup.authz.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.authz.service.AuthorizationService;
import com.fujitsu.ph.tsup.common.domain.Employee;

public class TsupAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private AuthorizationService authorizationService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub

		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(final Authentication authentication) {
		FpiUser user = (FpiUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Employee employee = authorizationService.findDetailsByUsername(user.getUserName());
		if(employee == null) {
			return "/register";
		} else {
			return "/dashboard";
		}
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
