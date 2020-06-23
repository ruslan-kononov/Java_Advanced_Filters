package ua.advanced.filter;

import ua.advanced.domain.UserRole;
import ua.advanced.shared.FilterService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;

@WebFilter("/product")
public class ProductFilter implements Filter {
    private FilterService filterService = FilterService.getFilterService();
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        filterService.doFilterValidation(request,response,chain,Arrays.asList(UserRole.USER, UserRole.ADMINISTRATOR));
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
