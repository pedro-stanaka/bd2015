package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DAOFactory;
import dao.TaxiDAO;
import model.TaxiPosition;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Describe purpose of this file HERE MTF!
 *
 * @author Pedro Tanaka <pedro.stanaka@gmail.com>
 */
@WebServlet(name = "TaxiController", urlPatterns = {
        "/taxis/randomtraj",
        "/taxis"
})
public class TaxiController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/taxis/randomtraj": {

                try {
                    DAOFactory factory = new DAOFactory();
                    TaxiDAO taxiDAO = factory.getTaxiDAO();

                    // Pegar posicoes
                    List<TaxiPosition> positions = taxiDAO.randomTrajectory();

                    // Instanciando conversor de objetos java para JSON
                    GsonBuilder builder = new GsonBuilder();
                    builder.setDateFormat("dd-MM-yyyy hh:mm:ss.S");
                    Gson gson = builder.create();

                    // Preparar resposta JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    PrintWriter out = response.getWriter();

                    // Conversão JSON e escrita da resposta
                    out.print(gson.toJson(positions));
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            }

            case "/taxis": {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/taxi/index.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }
}
