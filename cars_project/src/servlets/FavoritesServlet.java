package servlets;

import entities.Car;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.MainHelper;
import services.CarService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "FavoritesServlet")
public class FavoritesServlet extends HttpServlet {
    private CarService carService = new CarService();
    private UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        if(userService.getCurrentUser(request)!=null) {
            Configuration cfg = MainHelper.getConfig(getServletContext());

            Template tmpl = cfg.getTemplate("favorites.ftl");
            HashMap<String, Object> root = new HashMap<>();
            List<Car> favorites = carService.getAllFavoritesByUserId(request);
            root.put("favorites", favorites);
            try {
                tmpl.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }else {
            response.sendRedirect("/login");
        }
    }
}
