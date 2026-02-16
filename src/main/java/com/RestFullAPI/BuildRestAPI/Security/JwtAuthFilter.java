package com.RestFullAPI.BuildRestAPI.Security;

import com.RestFullAPI.BuildRestAPI.Entity.User;
import com.RestFullAPI.BuildRestAPI.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Slf4j  //for Logging purpose
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final AuthUtil authUtil;

    private final HandlerExceptionResolver handlerExceptionResolver;

    public JwtAuthFilter(UserRepository userRepository, AuthUtil authUtil, HandlerExceptionResolver handlerExceptionResolver) {
        this.userRepository = userRepository;
        this.authUtil = authUtil;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            logger.info("incoming request : {}" + request.getRequestURI());
            final String requestTokenheader = request.getHeader("Authorization");
//         Pass the data in Bearer Token => "Bearer hjkhghgfghsjhjdkjhghsj"
            if (requestTokenheader == null || !requestTokenheader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }

//         if you have get Token and starts with Bearer then =>
            String Token = requestTokenheader.split("Bearer ")[1]; // Like 0 means Bearer and 1 means hjkhjkhkhfggffgfdfg
            String username = authUtil.getUsernameFromToken(Token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userRepository.findByUsername(username).orElseThrow();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                //Save SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            filterChain.doFilter(request, response);
        }catch (Exception ex){//Handle the Exception Handling Send Jwtfilter to MVC then Exception is properly working
            handlerExceptionResolver.resolveException(request,response,null, ex);
        }
    }
}










