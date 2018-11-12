package services;

import dao.CarDAO;
import entities.Car;
import entities.CarClass;
import entities.Model;
import entities.Producer;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class CarService {
    private UserService userService = new UserService();
    private ClassService classService = new ClassService();
    private ProducerService producerService = new ProducerService();
    private ModelService modelService = new ModelService();
    private CarDAO carDAO = new CarDAO();

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getCarById(int id) {
        return carDAO.getCarById(id);
    }

    public List<Car> getAllFavoritesByUserId(HttpServletRequest request) {
        return carDAO.getAllFavorites(userService.getCurrentUser(request).getId());
    }

    public String addFavorite(HttpServletRequest request) {
        int carId = Integer.parseInt(request.getParameter("car_id"));
        int userId = userService.getCurrentUser(request).getId();
        List<Car> alreadyFav = carDAO.getAllFavorites(userId);
        boolean carAlreadyInFavs = false;
        for (Car car : alreadyFav) {
            if (car.getId() == carId) {
                carAlreadyInFavs = true;
            }
        }
        if (carAlreadyInFavs) {
            return "already";
        } else {
            carDAO.addFavorite(carId, userId);
            return "added";
        }

    }

    public void addCar(HttpServletRequest request, Part filePart, String path) {
        CarClass carClass = classService.getClassById(request);
        Model model = modelService.addModel(request);
        String color = request.getParameter("color");
        String transmission = request.getParameter("transmission");
        int horsepower = Integer.parseInt(request.getParameter("horsepower"));
        int capacity = Integer.parseInt(request.getParameter("seats"));
        int price = Integer.parseInt(request.getParameter("price"));
        String description = request.getParameter("description");
        carDAO.addCar(color, horsepower, capacity, price, transmission, model.getId(), carClass.getId(), description, filePart, path);

    }


    public void deleteFavorite(HttpServletRequest request) {
        carDAO.deleteFavorite(Integer.parseInt(request.getParameter("fav_id")), userService.getCurrentUser(request).getId());
    }

    public List<Car> getCarsByMask(HttpServletRequest request) {
        String mask = request.getParameter("text");
        return carDAO.getCarsByMask(mask);
    }

    public List<Car> sortedCarList(HttpServletRequest request) {
        return carDAO.sortCars(request.getParameter("carClass"), request.getParameter("producer"), request.getParameter("transmission"),
                request.getParameter("horsepower"), request.getParameter("seats"), request.getParameter("price"));
    }

    public void sortByTestResults(HttpServletRequest request) {
    }
}
