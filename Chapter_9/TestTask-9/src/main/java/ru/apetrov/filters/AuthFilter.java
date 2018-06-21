package ru.apetrov.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (!request.getRequestURI().contains("/signin")) {
            HttpSession session = request.getSession();
            synchronized (session) {
                if (session.getAttribute("login") == null) {
                    HttpServletResponse response = (HttpServletResponse) servletResponse;
                    response.sendRedirect(String.format("%s/signin", request.getContextPath()));
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
