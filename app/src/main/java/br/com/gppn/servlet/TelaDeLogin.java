package br.com.gppn.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/telaLogin")

public class TelaDeLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        System.out.println(usuario);
        //Car car = new Car();
        //CarDao carDao = new CarDao();

       // car.setName(carName);
       // carDao.createCar(car);




        req.getRequestDispatcher("index.html").forward(req, resp);

    }
}
