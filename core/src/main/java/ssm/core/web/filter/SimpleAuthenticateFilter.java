package ssm.core.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * @author jinqinghua@gmail.com
 * @version 2012/08/04 add sessionKey
 */
public class SimpleAuthenticateFilter extends OncePerRequestFilter {
	private String sessionKey;
	private String loginPage;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		if (!this.isAuthenticated(request, sessionKey)) {
			String contextPath = request.getContextPath();
			String rediretPath = contextPath + this.loginPage;
			rediretPath = StringUtils.replace(rediretPath, "//", "/");
			response.sendRedirect(rediretPath);
			return;
		}
		chain.doFilter(request, response);
	}

	private boolean isAuthenticated(HttpServletRequest request, String key) {
		return null == WebUtils.getSessionAttribute(request, key) ? false : true;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
}
