package br.com.gppn.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/telaCadastro")

public class TelaDeCadastro extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        System.out.println(nome);
        String sobrenome = req.getParameter("sobrenome");
        System.out.println(sobrenome);
        String endereco = req.getParameter("endereco");
        System.out.println(endereco);
        String telefone = req.getParameter("telefone");
        System.out.println(telefone);
        String email = req.getParameter("email");
        System.out.println(email);
        String senha = req.getParameter("senha");
        System.out.println(senha);
        String confirSenha = req.getParameter("confirSenha");
        System.out.println(confirSenha);


        //Car car = new Car();
        //CarDao carDao = new CarDao();

        // car.setName(carName);
        // carDao.createCar(car);


        req.getRequestDispatcher("index.html").forward(req, resp);

    }
}
