<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ToDo Web - Tareas</title>

    <!--CSS-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/WEB-INF/navBarMenu.jsp"/>

<c:if test="${empty(tarea)}">
    <!-- Botón agregar tarea -->
    <div class="container-fluid py-3 mb-4 bg-secondary">
        <div class="row">
            <div class="col-3">
                <a href="#" class="btn btn-primary btn-block" data-bs-toggle="modal" data-bs-target="#clientModal"
                   data-controls-modal="clientModal" data-bs-backdrop="static" data-bs-keyboard="false">
                    <i class="fas fa-plus"></i> Agregar Tarea
                </a>
            </div>
        </div>
    </div>

    <!-- Listado de tareas -->
    <div class="container-fluid mb-5">
        <c:if test="${!empty(success)}">
            <div class="row">
                <div class="col-10">
                    <div class="alert alert-success alert-dismissible">
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            ${success}
                    </div>
                </div>
            </div>
        </c:if>
        <div class="row">
            <div class="col-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de tareas</h4>
                    </div>
                    <c:choose>
                        <c:when test="${!empty(tareas)}">
                            <div class="card-body">
                                <table id="listTareas" class="table table-striped table-sm" cellspacing="0"
                                       width="100%">
                                    <thead class="bg-dark text-white">
                                    <tr>
                                        <th>No</th>
                                        <th>Titulo</th>
                                        <th>Fecha creacion</th>
                                        <th>Prioridad</th>
                                        <th>Estado</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="tarea" items="${tareas}" varStatus="status">
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${tarea.titulo}</td>
                                            <td>${tarea.fechaCreacion}</td>
                                            <td>${tarea.prioridad == 1 ? 'Baja' : tarea.prioridad == 2 ? 'Media': 'Alta'}</td>
                                            <td>${tarea.idEstado == 1 ? 'Pendiente' : tarea.idEstado == 2 ? 'En Proceso': 'Finalizada'}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/editarTarea?action=get&id=${tarea.id}"
                                                   class="btn btn-info btn-sm">Editar</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h4 class="pl-4">No hay tareas</h4>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="clientModal" tabindex="-1" aria-labelledby="clientModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header bg-dark text-white">
                    <h5 class="modal-title" id="clientModalLabel">Agregar Tarea</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                            onclick="$('#limpiar').click()"></button>
                </div>
                <div class="modal-body">
                    <form id="form-tarea" action="${pageContext.request.contextPath}/crearTarea" method="POST">
                        <%@include file="formTarea.jsp" %>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" form="form-tarea">Agregar</button>
                    <button id="limpiar" type="reset" class="btn btn-info" form="form-tarea">Limpiar</button>
                </div>
            </div>
        </div>
    </div>
</c:if>

<c:if test="${!empty(tarea)}">
    <div class="container-fluid mt-4">
        <div class="row d-flex justify-content-center">
            <div class="col-xl-4">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title">Editar tarea</h5>
                    </div>
                    <div class="card-body">
                        <form id="form-tarea" action="${pageContext.request.contextPath}/editarTarea?action=set" method="POST">
                            <%@include file="formTarea.jsp" %>
                    </div>
                    <div class="card-footer">
                        <button type="submit" class="btn btn-primary btn-block" form="form-tarea">Guardar cambios
                        </button>
                        <a href="${pageContext.request.contextPath}/listarTareas" class="btn btn-danger btn-block">Cancelar</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</c:if>

<!--JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>