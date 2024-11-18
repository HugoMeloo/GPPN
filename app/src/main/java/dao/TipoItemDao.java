package dao;

import config.ConnectionPoolConfig;
import model.TipoItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoItemDao {

        public void salvarNovoTipo(String descricao) throws Exception {
            String sql = "INSERT INTO TIPOS_ITENS (descricao) VALUES (?)";

            try (Connection connection = ConnectionPoolConfig.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, descricao);
                preparedStatement.executeUpdate();
            }
        }

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
            }

            return tipos;
        }
    }
