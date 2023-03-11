<c:if test="${!empty(tarea)}">
    <div class="mb-3">
        <label for="id" class="form-label">Id:</label>
        <input type="text" class="form-control" name="id" id="id" value="${tarea.id}" readonly>
    </div>
</c:if>

<div class="mb-3">
    <label for="titulo" class="form-label">*Titulo:</label>
    <input type="text" class="form-control" name="titulo" id="titulo" value="${tarea.titulo}" required>
</div>
<div class="mb-3">
    <label for="descripcion" class="form-label">Descripcion:</label>
    <textarea class="form-control" name="descripcion" id="descripcion" rows="3">${tarea.descripcion}</textarea>
</div>
<div class="mb-3">
    <label for="prioridad" class="form-label">*Prioridad:</label>
    <select class="form-select" name="prioridad" id="prioridad" required>
        <c:forEach items="${['Baja', 'Media', 'Alta']}" var="prioridad" varStatus="status">
            <option value="${status.index + 1}" ${tarea.prioridad == status.index + 1 ? 'selected' : ''}>${prioridad}</option>
        </c:forEach>
    </select>
</div>
<div class="mb-3">
    <label for="estado" class="form-label">*Estado:</label>
    <select class="form-select" name="estado" id="estado" required>
        <c:forEach items="${['Pendiente', 'En Proceso', 'Finalizada']}" var="estado" varStatus="status">
            <option value="${status.index + 1}" ${tarea.idEstado == status.index + 1 ? 'selected' : ''}>${estado}</option>
        </c:forEach>
    </select>
</div>
</form>