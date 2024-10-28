package br.com.gppn.servlet;

import dao.UsuarioDao;
import model.Usuario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent;

@WebServlet("/telaCadastro")

public class CadastrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String usuarioId = req.getParameter("id-user");
        String nome = req.getParameter("nome");
        String sobrenome = req.getParameter("sobrenome");
        String endereco = req.getParameter("endereco");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String confirmSenha = req.getParameter("confirmar-senha");

        System.out.println("Nome: " + nome);
        System.out.println("Senha: " + senha); // Debug

        // Validação das senhas
        if (senha != null && senha.equals(confirmSenha)) {
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = new Usuario(usuarioId, nome, sobrenome, endereco, telefone, email, senha);

            // Criação ou atualização do usuário
            if (usuarioId == null || usuarioId.isBlank()) {
                usuarioDao.createUser(usuario);
            } else {
                usuarioDao.updateUsuario(usuario);
            }

            resp.sendRedirect("login.jsp"); // Redireciona para a página de login
        } else {
            System.out.println("As senhas não correspondem ou estão nulas.");
            // Aqui você pode redirecionar ou mostrar uma mensagem de erro
            resp.sendRedirect("error.jsp"); // Exemplo: redirecionar para uma página de erro
        }
    }
}