package br.com.gppn.servlet;

import dao.TipoItemDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SalvarNovoTipo")
public class SalvarNovoTipo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8"); // Configura UTF-8 para o request
        String novoTipo = request.getParameter("novoTipo"); // Captura o novo tipo

        if (novoTipo != null && !novoTipo.trim().isEmpty()) {
            try {
                TipoItemDao dao = new TipoItemDao(); // Classe DAO para salvar no banco
                dao.salvarNovoTipo(novoTipo); // Salva o novo tipo no banco
                response.setStatus(HttpServletResponse.SC_OK); // Responde com sucesso
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Responde com erro interno
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Responde com erro de cliente
        }
    }
}

