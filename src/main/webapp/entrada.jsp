<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Seleccion de archivos</title>

    <!-- CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<jsp:include page="/WEB-INF/navBar.jsp"/>

<div class="container-fluid py-2 bg-light">
    <div class="row">
        <div class="col-xl-3">
            <h3 class="mt-2 ml-3">Seleccion de archivos</h3>
        </div>
    </div>
</div>

<form action="${pageContext.request.contextPath}/LecturaArchivo" method="POST" enctype="multipart/form-data">
    <div class="containter-fluid">
        <div class="row pl-5">
            <div class="col-md-6">
                <div class="my-2">
                    <label for="archivoEntrada" class="form-label font-weight-bold">Seleccione el archivo de entrada:</label>
                    <input type="file" class="form-control border" name="archivoEntrada" accept=".json">
                </div>
            </div>
        </div>
        <div class="row pl-5">
            <div class="col-md-4"></div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary btn-block mt-2">Continuar</button>
            </div>
        </div>
    </div>
</form>

<!-- JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
