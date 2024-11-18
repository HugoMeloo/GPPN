package br.com.gppn.servlet;

import dao.TipoItemDao;
import model.TipoItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet("/ListarTipos")
public class ListarTipos extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            TipoItemDao dao = new TipoItemDao();
            List<TipoItem> tipos = dao.listarTipos();

            JSONArray jsonArray = new JSONArray();
            for (TipoItem tipo : tipos) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", tipo.getId());
                jsonObject.put("descricao", tipo.getDescricao());
                jsonArray.put(jsonObject);
            }

            response.getWriter().print(jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
