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
import java.time.LocalDateTime;

@WebServlet("/crearTarea")
public class CrearTareaServlet extends HttpServlet {

    private TareaDB tareaDB;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection connection = (Connection) session.getAttribute("conexion");
        Usuario usuario = (Usuario) session.getAttribute("user");
        tareaDB = new TareaDB(connection);

        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String prioridad = request.getParameter("prioridad");
        String estado = request.getParameter("estado");

        Tarea tarea = new Tarea();
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setFechaCreacion(LocalDateTime.now());
        tarea.setPrioridad(Integer.parseInt(prioridad));
        tarea.setIdEstado(Integer.parseInt(estado));
        tarea.setIdUsuario(usuario.getId());

        tareaDB.insertar(tarea);
        request.setAttribute("success", "Tarea creada correctamente");
        request.getRequestDispatcher("listarTareas").forward(request, response);
    }
}
