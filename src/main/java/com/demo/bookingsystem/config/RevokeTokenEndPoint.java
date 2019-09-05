package com.demo.bookingsystem.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author Satish Ghonge
 *
 */
@FrameworkEndpoint
public class RevokeTokenEndPoint {

	@Autowired
	TokenStore tokenStore;

	@DeleteMapping("/oauth/token")
	@ResponseBody
	public void revokeToken(HttpServletRequest request) {
		String tokenId = request.getParameter("token");
		if (tokenId == null || tokenId.trim().equalsIgnoreCase(""))
			throw new AuthenticationCredentialsNotFoundException("Invalid Token");
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenId);
		if (accessToken == null)
			throw new AuthenticationCredentialsNotFoundException("Invalid Token");
		tokenStore.removeAccessToken(accessToken);
	}
}
