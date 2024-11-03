package dao;

import config.ConnectionPoolConfig;
import model.Empresa;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdutoDao {
    public void createProduto(Produto produto) {

        String SQL = "INSERT INTO PRODUTO (NOME, QUANTIDADE, PRECO, COD, TIPO, IMAGE) VALUES (?,?,?,?,?,?)";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, produto.getProduto());
            preparedStatement.setDouble(2, produto.getQuantidade());
            preparedStatement.setDouble(3, produto.getPrecoUni());
            preparedStatement.setString(4, produto.getCodigoItem());
            preparedStatement.setInt(5, produto.getTipoItem());
            preparedStatement.setString(6, produto.getImage());


            preparedStatement.execute();

            System.out.println("Produto cadastrado com sucesso");
            connection.close();
        } catch (Exception e) {
            System.out.println("Falha na conexão");
        }
    }

    public List<Produto> ExibirProdutos() {

        String SQL = "SELECT * FROM PRODUTO";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();


            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Produto> produtos = new ArrayList<>();

            while (resultSet.next()) {

                String produtoId = resultSet.getString("idProduto");
                String nomeProduto = resultSet.getString("nomeProduto");
                double quantidade = resultSet.getDouble("quantidade");
                double precoUni = resultSet.getDouble("precoUni");
                String codigoItem = resultSet.getString("codigoItem");
                int tipoItem = resultSet.getInt("tipoItem");
                String imagem = resultSet.getString("image");

                Produto produto = new Produto(produtoId, nomeProduto, quantidade, precoUni, codigoItem, tipoItem, imagem);

                produtos.add(produto);

            }

            System.out.println("success in select * produto");

            connection.close();

            return produtos;

        } catch (Exception e) {

            System.out.println("fail in database connection");

            return Collections.emptyList();

        }
    }

    public void deletedProdutoById(String produtoId) {
        String SQL = "DELETE PRODUTO WHERE ID = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();


            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, produtoId);
            preparedStatement.execute();

            System.out.println("success on deleted produto with id: " + produtoId);
            connection.close();

        } catch (Exception e) {
            System.out.println("fail in database connection");

        }
    }

    public void updateProduto(Produto produto) {

        String SQL = "UPDATE PRODUTO SET NOME = ?, QUANTIDADE = ?, PRECO = ?, COD = ?, TIPO = ?, IMAGE = ? WHERE ID = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, produto.getProduto());
            preparedStatement.setDouble(2, produto.getQuantidade());
            preparedStatement.setDouble(3, produto.getPrecoUni());
            preparedStatement.setString(4, produto.getCodigoItem());
            preparedStatement.setInt(5, produto.getTipoItem());
            preparedStatement.setString(6, produto.getImage());

            preparedStatement.execute();

            System.out.println("success in update Produto");
            connection.close();

        } catch (Exception e) {
            System.out.println("fail in database connection");
            System.out.println("Error: " + e.getMessage());


        }

    }
}
