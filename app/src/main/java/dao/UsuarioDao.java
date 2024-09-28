package dao;

import model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UsuarioDao {
    public void createUsuario (Usuario user) {

        String SQL = "INSERT INTO USUARIO (USER) VALUES (?)";

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa","sa");

            System.out.println("Sucess in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, user.getUsuario());
            preparedStatement.execute();

            System.out.println("success in insert user");
            connection.close();
        } catch (Exception e) {
            System.out.println("Fail in database connection");
        }
    }
}
