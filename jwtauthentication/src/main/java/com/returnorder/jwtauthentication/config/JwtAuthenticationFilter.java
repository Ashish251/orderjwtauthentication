package com.returnorder.jwtauthentication.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.returnorder.jwtauthentication.helper.JwtUtil;
import com.returnorder.jwtauthentication.loader.UserDataLoader;
import com.returnorder.jwtauthentication.service.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get jwt
		// bearer
		// validate
		log.info("JwtAuthenticationFilter :: doFilterInternal");
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				username = this.jwtUtil.extractUsername(jwtToken);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// security
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {
				log.debug("Token is not Validated");
			}

			
		}
		
		filterChain.doFilter(request, response);

	}

}
