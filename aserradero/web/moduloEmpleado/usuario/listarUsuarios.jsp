<%--
    Document   : usuarios
    Created on : 15-sep-2016, 19:23:13
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.empleado.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(false);
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Usuarios</title>
    </head>
    <body>
        <!--menu-->
        <%if(((String)sesion.getAttribute("rol")).equals("Administrador")){%>
            <%@ include file="/TEMPLATE/menu_admin.jsp" %>
        <%}else{%>
            <%@ include file="/TEMPLATE/menu.jsp" %>
        <%}%>

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>" >
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Usuarios</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Si el usuario que busca no aparece, agréguelo</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-busc">
                                <form method="POST" action="/aserradero/UsuarioController?action=buscar">
                                    <select name="nombre_campo" class="input-busc" >
                                        <option value="id_empleado">Id empleado</option>
                                        <option value="nombre_usuario">Nombre de usuario</option>
                                        <option value="email">Email</option>
                                    </select>
                                    <input type="text" class="input-busc" name="dato" placeholder="Escriba su búsqueda">
                                    <input type="submit" class="btn btn-success" value="Buscar">
                                </form>
                            </div>
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Empleado</th>
                                        <th>Nombre de usuario</th>
                                        <th>Email</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (Usuario usuario : usuarios) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + usuario.getId_empleado() + "\">" + usuario.getEmpleado() + "</a></td>"
                                                    + "<td>" + usuario.getNombre_usuario() + "</td>"
                                                    + "<td>" + usuario.getEmail() + "</td>"
                                                    + "<td><a class='btn btn-primary' href=\"/aserradero/UsuarioController?action=modificar&nombre_usuario=" + usuario.getNombre_usuario() + "\">Cambiar contraseña</a></td>"
                                                    + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/UsuarioController?action=eliminar&nombre_usuario=" + usuario.getNombre_usuario() + "';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-primary" value="Agregar usuario" onClick=" window.location.href = '/aserradero/UsuarioController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
