<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="table table-striped">
    <thead class="thead-dark">
        <tr>
            <th>Nombre</th>
            <th>Email</th>
            <th>Eliminar</th>
            <th>Editar</th>
        </tr>
    </thead>
    <tbody>
        <!-- Iteramos cada elemento de la lista de clientes -->
        <c:forEach var="cliente" items="${clientes}">
            <tr>
                <td>${cliente.nombre} ${cliente.apellido}</td>
                <td>${cliente.email}</td>
                <td><a href="${pageContext.request.contextPath}/clientes?action=delete&idCliente=${cliente.id}">Borrar</a></td>
                <td><a href="${pageContext.request.contextPath}/clientes?action=update&idCliente=${cliente.id}">Editar</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/clientes?action=create" type="button" class="btn btn-dark">Crear Usuario</a>

</br>
</br>

<form action="${pageContext.request.contextPath}/clientes?action=filter" method="POST">
    <div class="form-group">
        <label for="filtrar">Filtrar cliente por apellido</label>
        <input type="text" class="form-control" name="filter">
    </div>
    <button class="btn btn-dark" type="submit">Filtrar</button>
</form>