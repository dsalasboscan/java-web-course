<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Control de Clientes</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>

        <form action="${pageContext.request.contextPath}/clientes?action=update" method="POST">

            <div class="modal-body">
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" name="nombre" required value="${cliente.nombre}">
                </div>
                <div class="form-group">
                    <label for="apellido">Apellido</label>
                    <input type="text" class="form-control" name="apellido" required value="${cliente.apellido}">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" name="email" required value="${cliente.email}">
                </div>
                <div class="modal-footer">
                    <button class="btn btn-dark" type="submit">Guardar</button>
                </div>
                <input type="hidden" value="${cliente.id}" name="idCliente" />
        </form>

        </br></br>
        <a href="${pageContext.request.contextPath}/clientes" type="button" class="btn btn-dark">Ir al inicio</a>
    </body>
</html>



