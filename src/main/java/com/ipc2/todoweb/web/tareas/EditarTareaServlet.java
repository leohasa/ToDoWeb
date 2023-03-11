package com.ipc2.todoweb.web.tareas;

import com.ipc2.todoweb.data.TareaDB;
import com.ipc2.todoweb.model.Tarea;
import com.ipc2.todoweb.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/editarTarea")
public class EditarTareaServlet extends HttpServlet {

    private TareaDB tareaDB;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection connection = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");
        tareaDB = new TareaDB(connection);

        String action = request.getParameter("action");

        if (action.equals("get")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Tarea tarea = tareaDB.obtenerById(id).get();
            request.setAttribute("tarea", tarea);
            request.getRequestDispatcher("tarea/tareas.jsp").forward(request, response);
        } else if (action.equals("set")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            String prioridad = request.getParameter("prioridad");
            String estado = request.getParameter("estado");

            Tarea tarea = new Tarea();
            tarea.setId(id);
            tarea.setTitulo(titulo);
            tarea.setDescripcion(descripcion);
            tarea.setPrioridad(Integer.parseInt(prioridad));
            tarea.setIdEstado(Integer.parseInt(estado));
            tarea.setIdUsuario(usuario.getId());

            tareaDB.actualizar(tarea);
            request.setAttribute("success", "Tarea actualizada correctamente");
            request.getRequestDispatcher("listarTareas").forward(request, response);
        }
    }
}
