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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Usuario usuarioLogin;
    private UsuarioDB usuarioDB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conexion = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");

        if (usuario != null) {
            response.sendRedirect("menuPrincipal.jsp");
            return;
        }

        usuarioDB = new UsuarioDB(conexion);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (validarUsuario(username, password)) {
            session.setAttribute("user", usuarioLogin);
            response.sendRedirect("menuPrincipal.jsp");
        } else {
            request.setAttribute("errorLogin", "Credenciales incorrectas");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    public boolean validarUsuario(String username, String password) {
        var oUsuario = usuarioDB.obtenerUsuario(username, password);
        if (oUsuario.isEmpty()) return false;

        usuarioLogin = oUsuario.get();
        return true;
    }
}
