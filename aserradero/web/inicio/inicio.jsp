<%-- 
    Document   : inicio
    Created on : 11-sep-2016, 21:22:34
    Author     : lmarcoss
--%>

<%@page import="entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    response.setHeader( "Pragma", "no-cache" ); 
    response.addHeader( "Cache-Control", "must-revalidate" ); 
    response.addHeader( "Cache-Control", "no-cache" ); 
    response.addHeader( "Cache-Control", "no-store" ); 
    response.setDateHeader("Expires", 0); 
%>
<%
    HttpSession sesion = request.getSession(false);
    String nombre_usuario = (String)sesion.getAttribute("nombre_usuario");
    if(nombre_usuario.equals("")){
        response.sendRedirect("/aserradero/");
    }else if( nombre_usuario != null){
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Inicio</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <%
            String id_empleado = (String)sesion.getAttribute("id_empleado");
            String id_jefe = (String)sesion.getAttribute("id_jefe");
            String rol = (String)sesion.getAttribute("rol");
            String estatus = (String)sesion.getAttribute("estatus");
            out.print("Empleado: " +id_empleado);
            out.print("Jefe: " +id_jefe);
            out.print("Rol: " +rol);
            out.print("Estatus: " +estatus);
        %>
    </body>
</html>

<%
    }else{
        response.sendRedirect("/aserradero/");
    }
%>
