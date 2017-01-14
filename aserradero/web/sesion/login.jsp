<%-- 
    Document   : index
    Created on : 11-sep-2016, 16:29:36
    Author     : lmarcoss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    response.setHeader( "Pragma", "no-cache" ); 
    response.addHeader( "Cache-Control", "must-revalidate" ); 
    response.addHeader( "Cache-Control", "no-cache" ); 
    response.addHeader( "Cache-Control", "no-store" ); 
    response.setDateHeader("Expires", 0); 
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión</title>
        <link rel="stylesheet" href="/aserradero/dist/css/login.css">
        <script src="/aserradero/js/main.js"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">        
    </head>
    <body>
        <div class="login">
            <div class="login_container">
                <h1>Iniciar sesión</h1>
                <form action="Iniciar" method="post" id="forminiciar">
                    <p><input type="text" name="nombre_usuario" placeholder="Nombre de usuario" id="nombre_usuario"></p>
                    <p><input type="password" name="contrasenia" placeholder="Contraseña" id="contrasenia"></p>
                    <p><button type="submit" id="entrar">Entrar</button>
                    <a href="/aserradero/usuario/nuevoUsuario.jsp">Registrarme</a>
                </form>
            </div>
        </div>
    </body>
</html>
