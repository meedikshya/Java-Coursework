package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.StringUtils;

@WebFilter("/ProductFilter")
public class ProductFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get the requested URI
        String uri = httpRequest.getRequestURI();

        // Allow access to static resources (CSS, images) without checking login
        if (uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }

        // Check if the user is trying to access product-related URLs
        boolean isProductListPage = uri.endsWith(StringUtils.PRODUCT_LIST_PAGE);
        boolean isProductServlet = uri.endsWith(StringUtils.SERVLET_URL_ADD_PRODUCT);

        // If the user is not trying to access product-related URLs, allow access
        if (!isProductListPage && !isProductServlet) {
            chain.doFilter(request, response);
            return;
        }

        // If the user is trying to access product-related URLs, allow access
        // (assuming the user has already been authenticated by the authentication filter)
        // You can add additional checks here if needed
        
        // For example, if the product-related servlets require authentication,
        // you can check if the user is authenticated before allowing access
        // For demonstration, let's assume all product-related servlets require authentication
        boolean isAuthenticated = httpRequest.getSession().getAttribute("user") != null;
        if (isAuthenticated) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + StringUtils.PAGE_URL_ADMIN_DASHBOARD);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code
    }
}
