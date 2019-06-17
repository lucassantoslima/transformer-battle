/*
* Copyright 2018 Builders
*************************************************************
*Nome     : SimpleCORSFilter.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package com.aequilibrium.conf;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SimpleCORSFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal( final HttpServletRequest request, 
							final HttpServletResponse response, 
							final FilterChain chain)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with, Authorization");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH, HEAD");
		
		chain.doFilter(request, response);
	}

}
