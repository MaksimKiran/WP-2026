package mk.ukim.finki.wp2026.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp2026.service.CategoryService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/servlet/category"})
public class CategoryServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CategoryService categoryService;

    public CategoryServlet(SpringTemplateEngine springTemplateEngine, CategoryService categoryService) {
        this.springTemplateEngine = springTemplateEngine;
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("categories", categoryService.listCategories());
        context.setVariable("errorMessage", req.getParameter("errorMessage"));
        context.setVariable("ipAddress", req.getRemoteAddr());
        springTemplateEngine.process("categories.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            categoryService.create(req.getParameter("name"), req.getParameter("description"));
        } catch (IllegalArgumentException e) {
            resp.sendRedirect("/servlet/category?errorMessage=Invalid input for category");
            return;
        }
        resp.sendRedirect("/servlet/category");
    }
}
