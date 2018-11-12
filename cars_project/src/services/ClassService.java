package services;

import dao.ClassDAO;
import entities.CarClass;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClassService {
    private ClassDAO classDAO = new ClassDAO();

    public List<CarClass> getAlClasses(){
        return classDAO.getAllClasses();
    }


    public CarClass getClassById(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("class"));
        return classDAO.getClassById(id);
    }
}
