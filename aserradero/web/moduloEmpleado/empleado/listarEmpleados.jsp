<%--
    Document   : empleados
    Created on : 24-sep-2016, 14:04:34
    Author     : lmarcoss
--%>

<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(false);
    List<Empleado> empleados = (List<Empleado>) request.getAttribute("listaEmpleados");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Empleados</title>
    </head>
    <body>
        <%if (((String) sesion.getAttribute("rol")).equals("Administrador")) {%>
        <%@ include file="/TEMPLATE/menu_admin.jsp" %>
        <%} else {%>
        <%if(((String)sesion.getAttribute("rol")).equals("Administrador")){%>
            <%@ include file="/TEMPLATE/menu_admin.jsp" %>
        <%}else{%>
            <%@ include file="/TEMPLATE/menu.jsp" %>
        <%}%>
        <%}%>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>" >
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>LISTADO DE EMPLEADOS</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Empleados actuales de la empresa</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-busc" ><!-- Formulario para realizar búsquedas en la base de datos -->
                                <form method="POST" action="/aserradero/EmpleadoController?action=buscar" >
                                    <select class="input-busc" name="nombre_campo" >
                                        <option value="empleado">Empleado</option>
                                        <option value="roll">Roll</option>
                                        <option value="estatus">Status</option>
                                        <option value="jefe">Jefe</option>
                                    </select>
                                    <input type="text" class="input-busc" name="dato" placeholder="Escriba su búsqueda">
                                    <input type="submit" class="btn btn-success" value="Buscar">
                                </form>
                            </div><!-- Fin formulario de búsqueda -->
                            <table id="tabla" class="display cell-border" cellspacing="0" style="width: 100%;"><!-- Tabla que muestra los resultados de la consulta a la base de datos-->
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Empleado</th>
                                        <th>Rol</th>
                                        <th>Status</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (Empleado empleado : empleados) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + empleado.getId_empleado() + "\">" + empleado.getEmpleado() + "</a></td>"
                                                    + "<td>" + empleado.getRol() + "</td>"
                                                    + "<td>" + empleado.getEstatus() + "</td>"
                                                    + "<td><a  class='btn btn-warning' href=\"/aserradero/EmpleadoController?action=modificar&id_empleado=" + empleado.getId_empleado() + "&rol=" + empleado.getRol() + "\">Modificar</a></td>"
                                                    + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/EmpleadoController?action=eliminar&id_empleado=" + empleado.getId_empleado() + "&rol=" + empleado.getRol() + "';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table><!-- Fin de tabla -->
                            <div class="agregar_element"><!-- Botón agregar elementos -->
                                <input class="btn btn-primary" type="button" value="Agregar empleado" onClick=" window.location.href = '/aserradero/EmpleadoController?action=nuevo'">
                            </div><!-- Fin Agregar elementos-->
                        </div>
                    </div>
                </div>
            </div>
        </div>     
    </body>
</html>
