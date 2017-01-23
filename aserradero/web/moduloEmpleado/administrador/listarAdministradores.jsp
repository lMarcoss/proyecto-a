<%--
    Document   : administradores
    Created on : 16-oct-2016, 23:48:32
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.empleado.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(false);
    List<Administrador> administradores = (List<Administrador>) request.getAttribute("listaAdministradores");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Administradores</title>
    </head>
    <body>
        <!--menu-->
        <%if (((String) sesion.getAttribute("rol")).equals("Administrador")) {%>
        <%@ include file="/TEMPLATE/menu_admin.jsp" %>
        <%} else {%>
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <%}%>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Administradores</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Listado de administradores del sistema</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Administrador</th>
                                        <th>Cuenta inicial</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (Administrador administrador : administradores) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + administrador.getId_administrador() + "\">" + administrador.getNombre() + "</a></td>"
                                                    + "<td>" + administrador.getCuenta_inicial() + "</td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/AdministradorController?action=modificar&id_administrador=" + administrador.getId_administrador() + "\">Modificar cuenta</a></td>"
                                                    //                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/AdministradorController?action=eliminar&id_administrador="+administrador.getId_administrador()+"';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-primary" value="Nuevo administrador" onClick=" window.location.href = '/aserradero/AdministradorController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
