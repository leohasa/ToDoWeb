package com.ipc2.todoweb.web.tareas;

import com.ipc2.todoweb.data.TareaDB;
import com.ipc2.todoweb.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/listarTareas")
public class ListarTareasServlet extends HttpServlet {

    private TareaDB tareaDB;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection connection = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");
        tareaDB = new TareaDB(connection);

        request.setAttribute("tareas", tareaDB.listar(usuario.getId()));
        request.getRequestDispatcher("tarea/tareas.jsp").forward(request, response);
    }
}
