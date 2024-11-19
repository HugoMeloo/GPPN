package dao;

import config.ConnectionPoolConfig;
import model.DataPoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataPointDao {

    public List<DataPoint> getAllDataPoints() {
        List<DataPoint> dataPoints = new ArrayList<>();

        String sql = "SELECT NOME, PRECO, QUANTIDADE FROM PRODUTO"; // Consulta SQL
        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Obtendo os dados do banco
                String nome = resultSet.getString("NOME");
                double preco = resultSet.getDouble("PRECO");
                double quantidade = resultSet.getDouble("QUANTIDADE");

                // Criando objeto DataPoint e adicionando à lista
                dataPoints.add(new DataPoint(nome, preco, quantidade));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataPoints;
    }

    public List<DataPoint> getDataPointsArea() {
        List<DataPoint> dataPoints = new ArrayList<>();

        String sql = "SELECT NOME, TIPO, QUANTIDADE FROM PRODUTO"; // Consulta SQL
        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Obtendo os dados do banco
                String nome = resultSet.getString("NOME");
                String tipo = resultSet.getString("TIPO");
                double quantidade = resultSet.getDouble("QUANTIDADE");

                // Criando objeto DataPoint e adicionando à lista
                DataPoint dataPoint = new DataPoint(nome, tipo, quantidade);
                System.out.println("DataPoint Criado: " + dataPoint.getNome() + ", " + dataPoint.getTipo() + ", " + dataPoint.getQuantidade());
                dataPoints.add(dataPoint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataPoints;
    }

}