package services;

import dao.ModelDAO;
import entities.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ModelService {
    private ModelDAO modelDAO = new ModelDAO();


    public Model addModel(HttpServletRequest request) {
        return modelDAO.addModel(request.getParameter("model"),
                Integer.parseInt(request.getParameter("producer")));
    }

    public List<Model> getModelsByMask(HttpServletRequest request) {
        return modelDAO.getModelsByMask(request.getParameter("text"));
    }
}
