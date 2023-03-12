package com.ipc2.todoweb.web;

import com.ipc2.todoweb.data.UsuarioDB;
import com.ipc2.todoweb.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UsuarioDB usuarioDB;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection connection = (Connection) session.getAttribute("conexion");
        usuarioDB = new UsuarioDB(connection);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");

        Usuario usuario = new Usuario(nombre, username, password);
        if (usuarioDB.crear(usuario)) {
            Usuario usuarioRegistrado = usuarioDB.obtenerUsuario(username, password).get();
            session.setAttribute("user", usuarioRegistrado);
            response.sendRedirect("menuPrincipal.jsp");
        } else {
            request.setAttribute("error", "No se pudo registrar");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }


    }
}
