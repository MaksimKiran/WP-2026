package mk.ukim.finki.wp2026.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
class Category {
    private String name;
    private String description;
}

@WebServlet(name = "MessyCategoryServlet", urlPatterns = {"/messy/servlet/category"})
public class MessyCategoryServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private List<Category> categories;

    public MessyCategoryServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    public void addCategory(String name, String description) {
        categories.add(new Category(name, description));
    }

    @Override
    public void init() throws ServletException {
        categories = new ArrayList<>();
        addCategory("Movies", "Movies Category");
        addCategory("Books", "Books Category");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("ipAddress", req.getRemoteAddr());
        context.setVariable("userAgent", req.getHeader("user-agent"));
        context.setVariable("categories", categories);

        springTemplateEngine.process("categories.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCategory(req.getParameter("name"), req.getParameter("description"));
        resp.sendRedirect("/messy/servlet/category");
    }
}
