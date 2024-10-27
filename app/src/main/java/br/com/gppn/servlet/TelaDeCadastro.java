package br.com.gppn.servlet;

import dao.UsuarioDao;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/telaCadastro")

public class TelaDeCadastro extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.getRequestDispatcher("cadastro.jsp").forward(req, resp);

        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String username = req.getParameter("username");
            String password = req.getParameter("password");

            Usuario user = new Usuario(username, password);

            boolean isValidUser = new UsuarioDao().verifyCredentials(user);

            if (isValidUser) {

                req.getSession().setAttribute("loggedUser", username);

                resp.sendRedirect("ExibirEmpresas"); // Redireciona para página

            } else {

                req.setAttribute("message", "Invalid credentials!");

                req.getRequestDispatcher("cadastro.jsp").forward(req, resp);

            }

        }

    }

