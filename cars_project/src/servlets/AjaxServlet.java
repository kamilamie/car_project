package servlets;

import entities.Car;
import entities.Comment;
import entities.Model;
import org.json.JSONArray;
import org.json.JSONObject;
import services.CarService;
import services.CommentService;
import services.ModelService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AjaxServlet")
public class AjaxServlet extends HttpServlet {
    private CarService carService = new CarService();
    private UserService userService = new UserService();
    private CommentService commentService = new CommentService();
    private ModelService modelService = new ModelService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("ajax").equals("addToFavorites")) {
            String result = "";
            if (userService.getCurrentUser(request) != null) {
                result = carService.addFavorite(request);

            } else {
                result = "null_user";
            }
            JSONObject jo = new JSONObject();
            jo.put("result", result);
            response.setContentType("text/json");
            response.getWriter().println(jo.toString());
        } else if (request.getParameter("ajax").equals("addComment")) {
            String result = "";
            JSONObject jo = new JSONObject();
            if (userService.getCurrentUser(request) != null) {
                Comment comment = commentService.addComment(request);
                jo.put("username", userService.getCurrentUser(request).getUsername());
                jo.put("userId", userService.getCurrentUser(request).getId());
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                jo.put("date", df.format(comment.getDate()));
                jo.put("commentId", comment.getId());

                result = "success";
            } else {
                result = "null_user";
            }
            jo.put("result", result);


            response.setContentType("text/json");
            response.getWriter().println(jo.toString());
        } else if (request.getParameter("ajax").equals("removeFavorite")) {
            carService.deleteFavorite(request);
        } else if (request.getParameter("ajax").equals("deleteComment")) {
            commentService.deleteComment(request);
        } else if (request.getParameter("ajax").equals("getCarsList")) {
            List<Car> cars = carService.getCarsByMask(request);
            JSONArray ja = new JSONArray();
            for (Car car : cars) {
                JSONObject carJson = new JSONObject();
                carJson.put("id", car.getId());
                carJson.put("model", car.getModel().getProducer().getName() + " " + car.getModel().getName());
                carJson.put("horsepower", car.getHorsepower());
                carJson.put("color", car.getColor());
                carJson.put("price", car.getPrice());
                carJson.put("path", car.getPhoto_path());
                ja.put(carJson);
            }
            JSONObject jo = new JSONObject();
            jo.put("cars", ja);

            response.setContentType("text/json");
            response.getWriter().println(jo.toString());

        } else if (request.getParameter("ajax").equals("sortCars")) {
            JSONObject jo = new JSONObject();
            List<Car> cars = carService.sortedCarList(request);
            if(cars.size()>0) {
                JSONArray ja = new JSONArray();
                for (Car car : cars) {
                    JSONObject carJson = new JSONObject();
                    carJson.put("id", car.getId());
                    carJson.put("model", car.getModel().getProducer().getName() + " " + car.getModel().getName());
                    carJson.put("horsepower", car.getHorsepower());
                    carJson.put("color", car.getColor());
                    carJson.put("price", car.getPrice());
                    carJson.put("path", car.getPhoto_path());
                    ja.put(carJson);
                }
                jo = new JSONObject();
                jo.put("cars", ja);
            } else{
                jo.put("empty", "no suitable cars");
            }
            System.out.println(jo.toString());
            response.setContentType("text/json");
            response.getWriter().println(jo.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
