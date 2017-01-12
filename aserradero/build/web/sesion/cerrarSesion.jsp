<%-- 
    Document   : cerrarSesion
    Created on : 12-sep-2016, 13:07:26
    Author     : lmarcoss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerrar sesión</title>
    </head>
    <body>
        <%@ page session="true" %> 
        <%
            HttpSession sesion = request.getSession(true);
            sesion.invalidate();
            response.sendRedirect("/aserradero/");
        %>
    </body>
</html>
