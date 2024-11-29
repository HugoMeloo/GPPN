package dao;

import config.ConnectionPoolConfig;
import model.TipoItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TipoItemDao {

    // Salvar novo tipo e retornar o ID gerado
    public int salvarNovoTipo(String descricao) throws Exception {
        String sql = "INSERT INTO TIPOS_ITENS (descricao) VALUES (?)";
        int generatedId = -1;

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, descricao);
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    generatedId = resultSet.getInt(1); // Obtém o ID gerado
                } else {
                    throw new Exception("Erro ao salvar novo tipo: ID não gerado.");
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao salvar novo tipo: " + e.getMessage(), e);
        }

        return generatedId;
    }


    // Listar todos os tipos
    public List<TipoItem> listarTipos() throws Exception {
        String sql = "SELECT id, descricao FROM TIPOS_ITENS";
        List<TipoItem> tipos = new ArrayList<>();

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                TipoItem tipo = new TipoItem();
                tipo.setId(resultSet.getInt("id"));
                tipo.setDescricao(resultSet.getString("descricao"));
                tipos.add(tipo);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao listar tipos: " + e.getMessage(), e);
        }

        return tipos;
    }
    public String buscarDescricaoPorId(int id) throws Exception {
        String sql = "SELECT descricao FROM TIPOS_ITENS WHERE id = ?";
        String descricao = null;

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id); // Define o ID no parâmetro
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    descricao = resultSet.getString("descricao"); // Obtém a descrição
                } else {
                    throw new Exception("Tipo de item com ID " + id + " não encontrado.");
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao buscar descrição por ID: " + e.getMessage(), e);
        }

        return descricao;
    }
}
