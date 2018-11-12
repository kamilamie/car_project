package services;

import dao.ClassDAO;
import dao.ProducerDAO;
import entities.CarClass;
import entities.Producer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProducerService {

    private ProducerDAO producerDAO = new ProducerDAO();

    public List<Producer> getAllProducers(){
        return producerDAO.getAllProducers();
    }

    public Producer getProducerById(HttpServletRequest request){
        return producerDAO.getProducerById(Integer.parseInt(request.getParameter("producer")));
    }
}
