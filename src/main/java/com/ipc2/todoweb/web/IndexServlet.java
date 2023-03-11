package com.ipc2.todoweb.web;

import com.ipc2.todoweb.data.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Crear conexión a la base de datos y guardarla en el ambito de sesión
        Conexion conexion = new Conexion();

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        session.setAttribute("conexion", conexion.obtenerConexion());

        response.sendRedirect("login.jsp");
    }
}
