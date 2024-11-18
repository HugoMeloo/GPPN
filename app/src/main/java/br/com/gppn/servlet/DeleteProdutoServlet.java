package br.com.gppn.servlet;

import dao.EmpresaDao;
import dao.ProdutoDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeletarProduto")

public class DeleteProdutoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String produtoId = req.getParameter("idProduto");

        new ProdutoDao().deletedProdutoById(produtoId);

        resp.sendRedirect("/ExibirProdutos");

    }

}

