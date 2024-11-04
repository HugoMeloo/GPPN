package dao;

import config.ConnectionPoolConfig;
import model.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmpresaDao {

    public void createEmpresa(Empresa empresa) {

        String SQL = "INSERT INTO EMPRESA (NOME, ENDERECO, TELEFONE, SERVICO, EMAIL, IMAGE) VALUES (?,?,?,?,?,?)";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, empresa.getNome());
            preparedStatement.setString(2, empresa.getEndereco());
            preparedStatement.setString(3, empresa.getTelefone());
            preparedStatement.setString(4, empresa.getServico());
            preparedStatement.setString(5, empresa.getEmail());
            preparedStatement.setString(6, empresa.getImage());

            preparedStatement.execute();

            System.out.println("Empresa cadastrada com sucesso");
            connection.close();
        } catch (Exception e) {
            System.out.println("Falha na conexão");
            e.printStackTrace();

        }
    }

    public List<Empresa> ExibirEmpresas() {

        String SQL = "SELECT * FROM EMPRESA";
        List<Empresa> empresas = new ArrayList<>();

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            if (connection == null) {
                System.out.println("Conexão falhou: conexão é nula.");
                return Collections.emptyList();
            }

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String empresaId = resultSet.getString("id");
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");
                String telefone = resultSet.getString("telefone");
                String servico = resultSet.getString("servico");
                String email = resultSet.getString("email");
                String imagem = resultSet.getString("image");

                Empresa empresa = new Empresa(empresaId, nome, endereco, telefone, servico, email, imagem);
                empresas.add(empresa);
            }

            System.out.println("Empresas recuperadas: " + empresas.size());
            connection.close();
            return empresas;

        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
            e.printStackTrace(); // Imprime a stack trace para detalhes do erro
            return Collections.emptyList();
        }
    }

    public void deletedEmpresaById(String empresaId) {
        String SQL = "DELETE EMPRESA WHERE ID = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();


            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, empresaId);
            preparedStatement.execute();

            System.out.println("success on deleted empresa with id: " + empresaId);
            connection.close();

        } catch (Exception e) {
            System.out.println("fail in database connection");

        }
    }

    public void updateEmpresa(Empresa empresa) {

        String SQL = "UPDATE EMPRESA SET NOME = ?, ENDERECO = ?, TELEFONE = ?, SERVICO = ?, EMAIL = ?, IMAGE = ? WHERE ID = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, empresa.getNome());
            preparedStatement.setString(2, empresa.getEndereco());
            preparedStatement.setString(3, empresa.getTelefone());
            preparedStatement.setString(4, empresa.getServico());
            preparedStatement.setString(5, empresa.getEmail());
            preparedStatement.setString(6, empresa.getImage());
            preparedStatement.setInt(7, Integer.parseInt(empresa.getId()));


            preparedStatement.execute();

            System.out.println("success in update Empresa");
            connection.close();

        } catch (Exception e) {
            System.out.println("fail in database connection");
            System.out.println("Error: " + e.getMessage());


        }

    }
}

