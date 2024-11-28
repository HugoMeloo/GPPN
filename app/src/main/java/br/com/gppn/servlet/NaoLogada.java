package br.com.gppn.servlet;

import dao.EmpresaDao;
import model.Empresa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/Empresas"})

public class NaoLogada extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Empresa> empresas = new EmpresaDao().ExibirEmpresas();

        if (empresas.isEmpty()) {
            System.out.println("Nenhuma empresa encontrada.");
        }


        req.setAttribute("empresas", empresas);
        req.getRequestDispatcher("/empresas.jsp").forward(req, resp);
    }
}

