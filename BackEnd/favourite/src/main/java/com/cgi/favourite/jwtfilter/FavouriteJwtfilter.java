package com.cgi.favourite.jwtfilter;

import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.Enumeration;
/* This class implements the custom filter by extending org.springframework.web.filter.GenericFilterBean.  
 * Override the doFilter method with ServletRequest, ServletResponse and FilterChain.
 * This is used to authorize the API access for the application.
 */


public class FavouriteJwtfilter extends GenericFilterBean{

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       final HttpServletRequest request=(HttpServletRequest) req;
       
       System.out.println("request method :  " + request.getMethod());
       System.out.println("content type :  " + request.getContentType());


       if(request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
           chain.doFilter(request, response);
       } 
       
       final String authHeader=request.getHeader("Authorization");
       Enumeration<String>    allHeaders =  request.getHeaderNames();
       System.out.println("All othere headers :");
       while(allHeaders.hasMoreElements())
       {
    	   System.out.println(allHeaders.nextElement());
       }
       System.out.println("req" + request.getHeaderNames());
       System.out.println("authorization"+authHeader);
       
       final String authHeader1=request.getHeader("token");
       System.out.println("authorization1"+authHeader1);
       
       
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
           String userEmail=claims.getSubject();
           HttpSession httpSession=request.getSession();
           httpSession.setAttribute("loggedInUserId", userEmail);
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


