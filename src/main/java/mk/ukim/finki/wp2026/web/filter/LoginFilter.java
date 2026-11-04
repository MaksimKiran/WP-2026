package mk.ukim.finki.wp2026.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp2026.model.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "auth-filter", urlPatterns = "/*",
    dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
    initParams = @WebInitParam(name = "ignore-paths", value = "/login, /register, /css")
)
public class LoginFilter implements Filter {
    private List<String> ignorePaths;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.ignorePaths = Arrays.stream(filterConfig.getInitParameter("ignore-paths").split(","))
                .map(String::trim)
                .toList();
    }

    private boolean isIgnored(String path) {
        return this.ignorePaths.stream()
                .anyMatch(p -> path.equals(p) || path.startsWith(p + "/"));
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User loggedInUser = (User) req.getSession().getAttribute("user");
        String path = req.getServletPath();

        if (loggedInUser == null && !isIgnored(path)) {
            resp.sendRedirect("/login");
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
