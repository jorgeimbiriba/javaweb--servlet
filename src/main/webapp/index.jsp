<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br/>
<a href="http://localhost:8080/aranoua_javaweb_servlet_war/lista-pessoas">Lista Servlet</a>
<a href="http://localhost:8080/aranoua_javaweb_servlet_war/consultar-pessoa?id=1">Pessoa de ID 1</a>
<a href="http://localhost:8080/aranoua_javaweb_servlet_war/consulta-pessoa?id=4">Pessoa de ID 4</a>
<a href="http://localhost:8080/aranoua_javaweb_servlet_war/consulta-pessoa?id=5">Pessoa de ID 5</a>

</body>
</html>