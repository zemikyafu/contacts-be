package com.contact.backend.contactsbe.security;


//import com.contact.backend.contactsbe.services.CustomUserDetailsService;
//import com.contact.backend.contactsbe.services.CustomUserDetailsServiceImpl;
//import com.contact.backend.contactsbe.services.CustomUserDetailsService;
import com.contact.backend.contactsbe.services.CustomUserDetailsService;
import com.contact.backend.contactsbe.services.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.filter.OncePerRequestFilter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public  class JwtAuthenticationFilter extends OncePerRequestFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
//			if (response instanceof HttpServletResponse){
//
//				String requestOrigin = request.getHeader("Origin");
//				response.setHeader("Access-Control-Allow-Origin", requestOrigin);
//				response.setHeader("Access-Control-Allow-Credentials", "true");
//				//response.setHeader("Access-Control-Allow-Methods", "*");
//				response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
//				response.setHeader("Access-Control-Max-Age", "3600");
//				response.setHeader("Access-Control-Allow-Headers", "*");
//			}

			String jwt = getJwtFromRequest(request);
			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
				Long userId = tokenProvider.getUserIdFromJWT(jwt);
                String subject=tokenProvider.getSubjetcFromJWT(jwt);
                System.out.println("dofilter subject "+subject);
				 UserDetails userDetails = customUserDetailsService.loadUserById(userId);

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			{

			}
		} catch (Exception ex) {
			System.out.println("exception "+ex);
			LOGGER.error("Could not set user authentication in security context", ex);
		}

		filterChain.doFilter(request, response);
	}



	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		System.out.println("request bearerToken "+bearerToken);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
