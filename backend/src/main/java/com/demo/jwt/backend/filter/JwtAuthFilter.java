package com.demo.jwt.backend.filter;

import com.demo.jwt.backend.provider.UserAuthProvider;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;
     @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String header = request.getHeader(HttpHeaders.AUTHORIZATION);
         System.out.println(header);
       if(header != null)
       {
           String element[] =header.split(" ");
           if(element.length == 2 && "Bearer".equals(element[0]) && element[1] != "null"){
               System.out.println(element[1]);
               try{
                   SecurityContextHolder.getContext().setAuthentication(userAuthProvider.validateToken(element[1]));
              }
               catch (RuntimeException e){
                   SecurityContextHolder.clearContext();
                   throw  e;
               }
           }
       }
       filterChain.doFilter(request,response);
    }
}
