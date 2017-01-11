<%--
    Document   : inicio
    Created on : 11-sep-2016, 21:22:34
    Author     : lmarcoss
--%>

<%@page import="entidades.empleado.Usuario"%>
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
            if (rol.equals("Administrador") || rol.equals("Empleado") || rol.equals("Vendedor")) {
        %>
        <div class="container">
           <div class="perfil">
               <img src="/aserradero/dist/images/avatar.png" alt="" class="avatar">
                <%
                    out.print("<span class='l1id'>Empleado: </span>");
                    out.print("<span class='l2id'>"+id_empleado+"</span>");
                    out.print("<span class='l1id'> Jefe: </span>");
                    out.print("<span class='l2id'>"+id_jefe+"</span>");
                    out.print("<span class='l1rol'>Rol: </span>");
                    out.print("<span class='l2rol'>"+rol+"</span>");
                    out.print("<span class='l1estatus'>Estatus: </span>");                    
                    out.print("<span class='l2estatus'>"+estatus+"</span>");                    
                    //out.print("<div class='logoestatus'></div>");
                    out.print("<div class='divisor'></div>");
                }else{
                    System.out.println("Sólo Administrador, empleado y vendedor pueden iniciar sesión");
                    sesion.invalidate();
                    response.sendRedirect("/aserradero/");
                }
                %>
            </div> 
        </div>            
    </body>
</html>

<%
    }else{
        response.sendRedirect("/aserradero/");
    }
%>