<%--
    Document   : nuevoEmpleado
    Created on : 24-sep-2016, 14:22:52
    Author     : lmarcoss
--%>

<%@page import="entidades.empleado.Administrador"%>
<%@page import="entidades.registros.Persona"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(false);
    List<Persona> personas = (List<Persona>) request.getAttribute("personas");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%if (((String) sesion.getAttribute("rol")).equals("Administrador")) {%>
        <%@ include file="/TEMPLATE/menu_admin.jsp" %>
        <%} else {%>
        <%if(((String)sesion.getAttribute("rol")).equals("Administrador")){%>
            <%@ include file="/TEMPLATE/menu_admin.jsp" %>
        <%}else{%>
            <%@ include file="/TEMPLATE/menu.jsp" %>
        <%}%>
        <%}%>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Nuevo empleado</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"></h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/EmpleadoController?action=insertar" method="post" id="formregistro">
                                <div class="form-group">
                                    <label class="control-label">Empleado:</label>
                                    <input type="hidden" name="id_empleado" value="" readonly="">
                                        <!-- Se calcula en el crud-->
                                        <!-- Seleccionar persona que se va a asignar como empleado-->
                                        <select class="form-control" name="id_persona" required="" title="Si no existe la persona que busca, primero agreguelo en la lista de personas">
                                            <option></option>
                                            <% for (Persona persona : personas) { out.print("<option value='" + persona.getId_persona() + "'>" + persona.getNombre() + "</option>"); } %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Rol:</label>
                                        <select class="form-control" name="rol" required="">
                                            <option></option>
                                            <option value="Empleado">Empleado</option>
                                            <option value="Chofer">Chofer</option>
                                            <option value="Vendedor">Vendedor</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Estatus</label>
                                        <td>
                                            <select class="form-control" name="estatus" required="">
                                                <option value="Activo">Activo</option>
                                            </select>

                                        </div>
                                        <div class="form-group">
                                            <a href="/aserradero/EmpleadoController?action=listar"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
                                            <input type="submit" class="btn btn-success" value="Guardar"/>
                                        </div>
                                    </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
