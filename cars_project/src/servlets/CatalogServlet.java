package servlets;

import entities.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.MainHelper;
import services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "CatalogServlet")
public class CatalogServlet extends HttpServlet {
    private UserService userService = new UserService();
    private CarService carService = new CarService();
    private CommentService commentService = new CommentService();
    private ClassService classService = new ClassService();
    ProducerService producerService= new ProducerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            Configuration cfg = MainHelper.getConfig(getServletContext());
            Template tmpl = cfg.getTemplate("catalog.ftl");
            HashMap<String, Object> root = new HashMap<>();
            List<Car> cars = carService.getAllCars();
            root.put("cars", cars);
            List<CarClass> classes = classService.getAlClasses();
            List<Producer> producers = producerService.getAllProducers();
            root.put("classes",classes);
            root.put("producers", producers);
            try {
                tmpl.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }

        } else if (path.split("/").length == 2) {
            int carId = Integer.parseInt(path.split("/")[1]);
            Configuration cfg = MainHelper.getConfig(getServletContext());
            Template tmpl = cfg.getTemplate("car.ftl");
            HashMap<String, Object> root = new HashMap<>();
            Car car = carService.getCarById(carId);
            List<Comment> comments = commentService.getAllCommentsByCarId(carId);
            root.put("car", car);
            root.put("comments", comments);
            root.put("user", userService.getCurrentUser(request));
            try {
                tmpl.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }

    }
}
