package br.com.gppn.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DataPointDao;
import model.DataPoint;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/GetChartData")
public class ExibirGraficos extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            DataPointDao dao = new DataPointDao();
            List<DataPoint> dataPoints = dao.getAllDataPoints();

            JSONArray jsonArray = new JSONArray();
            String[] cores = {
                    "#FF6384", "#36A2EB", "#FFCE56", "#4BC0C0", "#9966FF", "#FF9F40",
                    "#E74C3C", "#8E44AD", "#3498DB", "#1ABC9C", "#2ECC71", "#F1C40F",
                    "#E67E22", "#34495E", "#D35400", "#C0392B", "#BDC3C7", "#7F8C8D",
                    "#9B59B6", "#16A085"
            };
            int i = 0;

            for (DataPoint dataPoint : dataPoints) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("label", dataPoint.getNome());
                jsonObject.put("value", dataPoint.getValorTotal());
                jsonObject.put("color", cores[i % cores.length]); // Atribuindo cor
                jsonArray.put(jsonObject);
                i++;
            }

            out.print(jsonArray.toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Erro ao processar os dados.\"}");
            e.printStackTrace();
        } finally {
            out.flush();
        }
    }
}

