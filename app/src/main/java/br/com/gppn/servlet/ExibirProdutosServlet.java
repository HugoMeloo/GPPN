package br.com.gppn.servlet;

import dao.ProdutoDao;
import model.Produto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/ExibirProdutos", "/admin/ExibirProdutos"})


public class ExibirProdutosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Produto> produtos = new ProdutoDao().ExibirProdutos();

        req.setAttribute("produtos", produtos);

        req.getRequestDispatcher("/estoque.jsp").forward(req, resp);
    }
}
