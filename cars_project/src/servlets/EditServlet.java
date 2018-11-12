package servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.MainHelper;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "EditServlet")
public class EditServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String result = userService.updateUser(request);
        if (result.equals("success")) {
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/edit?problem=" + result);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        if (userService.getCurrentUser(request) != null) {
            PrintWriter pw = response.getWriter();
            Configuration cfg = MainHelper.getConfig(getServletContext());
            Template tmpl = cfg.getTemplate("edit.ftl");
            HashMap<String, Object> root = new HashMap<>();
            root.put("user", userService.getCurrentUser(request));

            if (request.getParameter("problem") != null) {
                root.put("problem", request.getParameter("problem"));
            }
            try {
                tmpl.process(root, response.getWriter());
            } catch (
                    TemplateException e) {
                e.printStackTrace();
            }
            pw.close();

        } else {
            response.sendRedirect("/login");
        }
    }
}
