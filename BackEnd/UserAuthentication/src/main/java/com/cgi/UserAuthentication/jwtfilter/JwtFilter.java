package com.cgi.UserAuthentication.jwtfilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       final HttpServletRequest request=(HttpServletRequest) req;
       final String authHeader=request.getHeader("Authorization");
       
       System.out.println("authHeader:" + authHeader);
       if(authHeader==null|| !authHeader.startsWith("Bearer "))
       {
        throw new ServletException("missing or invalid authorixation header");   
       }
       final String compactJws = authHeader.substring(7);
       try
       {
           JwtParser jwtParser=Jwts.parser().setSigningKey("secretKey");
           Jwt jwt=jwtParser.parse(compactJws);
           Claims claims=(Claims)jwt.getBody();
           request.setAttribute("claims",claims);
           String userId=claims.getSubject();
           HttpSession httpSession=request.getSession();
           httpSession.setAttribute("loggedInUserId", userId);
       }
       catch (SignatureException e) {
           e.printStackTrace();
           throw new ServletException("invalid token");
       }catch(MalformedJwtException jwtException)
       {
           throw new ServletException("jwt is malformed");
       }
       
chain.doFilter(request, response);
    }
}

