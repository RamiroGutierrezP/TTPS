package com.ttps.proyecto.filters;

import com.ttps.proyecto.service.TokenService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static java.util.Objects.isNull;

@WebFilter(filterName = "jwt-auth-filter", urlPatterns = "/api/ttps/*")
public class JWTAuthenticationFilter implements Filter {

    private FilterConfig filterConf;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConf = filterConfig;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getRequestURI().contains("/api/ttps/users/")) {
            chain.doFilter(request, response);
            return;
        }

        String token = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (isNull(token) || !TokenService.validateToken(token)) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        this.filterConf = null;
    }

}
