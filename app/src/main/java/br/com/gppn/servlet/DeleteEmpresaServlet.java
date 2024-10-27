package br.com.gppn.servlet;

import dao.EmpresaDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeletarEmpresa")

public class DeleteEmpresaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String empresaId = req.getParameter("id");

        new EmpresaDao().deletedEmpresaById(empresaId);

        resp.sendRedirect("/ExibirEmpresas");

    }

}

