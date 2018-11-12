package servlets;

import entities.Car;
import entities.CarClass;
import entities.Model;
import entities.Producer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.MainHelper;
import services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "AddCarServlet")
@MultipartConfig
public class AddCarServlet extends HttpServlet {

    private UserService userService = new UserService();
    private ClassService classService = new ClassService();
    private ProducerService producerService = new ProducerService();
    private CarService carService = new CarService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Part filePart = request.getPart("file");
        carService.addCar(request, filePart, getServletContext().getRealPath("/files/cars"));
        response.sendRedirect("/catalog");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        if (userService.getCurrentUser(request) != null) {
            if (userService.getCurrentUser(request).getLogin().equals("admin")) {
                PrintWriter pw = response.getWriter();
                Configuration cfg = MainHelper.getConfig(getServletContext());
                Template tmpl = cfg.getTemplate("addCar.ftl");
                HashMap<String, Object> root = new HashMap<>();
                List<CarClass> classes = classService.getAlClasses();
                List<Producer> producers = producerService.getAllProducers();
                root.put("classes",classes);
                root.put("producers", producers);
                try {
                    tmpl.process(root, response.getWriter());
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
                pw.close();
            } else {
                response.sendRedirect("/profile");
            }
        } else {
            response.sendRedirect("/login");
        }
    }
}
