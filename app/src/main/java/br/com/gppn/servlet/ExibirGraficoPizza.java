package br.com.gppn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DataPointDao;
import model.DataPoint;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/GetChartDataArea")
public class ExibirGraficoPizza extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Instanciar DAO e obter os pontos de dados
            DataPointDao dao = new DataPointDao();
            List<DataPoint> dataPoints = dao.getDataPointsArea();

            if (dataPoints == null || dataPoints.isEmpty()) {
                System.err.println("Nenhum dado encontrado no banco.");
                out.print("[]"); // Retornar um JSON vazio
                return;
            }

            // Contagem de tipos
            Map<String, Integer> tipoContagem = new HashMap<>();
            for (DataPoint dataPoint : dataPoints) {
                String tipo = dataPoint.getTipo();
                int quantidade = (int) dataPoint.getQuantidade();
                tipoContagem.put(tipo, tipoContagem.getOrDefault(tipo, 0) + quantidade);
            }

            // Converter as contagens para JSON
            JSONArray jsonArray = new JSONArray();
            String[] cores = {
                    "#FF6384", "#36A2EB", "#FFCE56", "#4BC0C0", "#9966FF", "#FF9F40",
                    "#E74C3C", "#8E44AD", "#3498DB", "#1ABC9C", "#2ECC71", "#F1C40F",
                    "#E67E22", "#34495E", "#D35400", "#C0392B", "#BDC3C7", "#7F8C8D",
                    "#9B59B6", "#16A085"
            };
            int i = 0;

            for (Map.Entry<String, Integer> entry : tipoContagem.entrySet()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("label", entry.getKey()); // Nome do tipo
                jsonObject.put("value", entry.getValue()); // Contagem do tipo
                jsonObject.put("color", cores[i % cores.length]); // Atribuindo cores
                jsonArray.put(jsonObject);
                System.out.println("Adicionado ao JSON: " + jsonObject);
                i++;
            }

            // Retornar o JSON
            System.out.println("JSON Final: " + jsonArray.toString());
            out.print(jsonArray.toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}



