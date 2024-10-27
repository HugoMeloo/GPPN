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

@WebServlet({"/ExibirEmpresas", "/admin/ExibirEmpresas"})

public class ExibirEmpresas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Empresa> empresas = new EmpresaDao().ExibirEmpresas();

        req.setAttribute("empresas", empresas);

        req.getRequestDispatcher("/empresa.jsp").forward(req, resp);
    }

}

