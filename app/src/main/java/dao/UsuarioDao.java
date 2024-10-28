package dao;

import config.ConnectionPoolConfig;
import model.Empresa;
import model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao {

    public void createUser(Usuario usuario) {

        String SQL = "INSERT INTO USUARIO (NOME, SOBRENOME, ENDERECO, TELEFONE, EMAIL, SENHA) VALUES (?,?,?,?,?,?)";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSobrenome());
            preparedStatement.setString(3, usuario.getEndereco());
            preparedStatement.setString(4, usuario.getTelefone());
            preparedStatement.setString(5, usuario.getEmail());
            preparedStatement.setString(6, usuario.getSenha());

            preparedStatement.execute();

            System.out.println("Usuario cadastrada com sucesso");
            connection.close();
        } catch (Exception e) {
            System.out.println("Falha na conexão");
        }
    }

    public void updateUsuario(Usuario usuario) {

        String SQL = "UPDATE EMPRESA SET NOME = ?, SOBRENOME = ?, ENDERECO = ?, TELEFONE = ?, EMAIL = ? WHERE ID = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSobrenome());
            preparedStatement.setString(3, usuario.getEndereco());
            preparedStatement.setString(4, usuario.getTelefone());
            preparedStatement.setString(5, usuario.getEmail());

            preparedStatement.execute();

            System.out.println("success in update usuario");
            connection.close();

        } catch (Exception e) {
            System.out.println("fail in database connection");
            System.out.println("Error: " + e.getMessage());


        }

    }

    public boolean verifyCredentials(Usuario user) {

        String SQL = "SELECT * FROM USUARIO WHERE EMAIL = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("success in select username");

            while (resultSet.next()) {

                String password = resultSet.getString("senha");

                if (password.equals(user.getSenha())) {
                    return true;
                }

            }

            connection.close();

            return false;

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

            return false;

        }
    }
}
