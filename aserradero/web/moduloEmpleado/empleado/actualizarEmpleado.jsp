<%--
    Document   : actualizarEmpleado
    Created on : 24-sep-2016, 22:13:01
    Author     : lmarcoss
--%>

<%@page import="entidades.empleado.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(false);
    Empleado empleado = (Empleado) request.getAttribute("empleado");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Actualizar</title>
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
                    <h2>Actualizar datos de empleado</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"></h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/EmpleadoController?action=actualizar" method="post" id="formregistro">
                                <div class="form-group">
                                        <input type="hidden" name="id_empleado" value="<%=empleado.getId_empleado()%>" readonly="">
                                        <input type="hidden" name="id_persona" value="<%=empleado.getId_persona()%>" readonly="">
                                        <input type="hidden" name="id_jefe" value="<%=empleado.getId_jefe()%>" readonly="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Nombre empleado:</label>
                                        <input class="form-control" name="empleado" value="<%=empleado.getEmpleado()%>" readonly="">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Rol:</label>
                                    <select class="form-control" name="rol" required="">
                                        <%if (empleado.getRol().equals("Administrador")) {%>
                                        <option value="Administrador" selected="">Administrador</option>
                                        <%} else if (empleado.getRol().equals("Empleado")) {%>
                                        <option value="Empleado" selected="">Empleado</option>
                                        <option value="Chofer">Chofer</option>
                                        <option value="Vendedor">Vendedor</option>
                                        <%} else if (empleado.getRol().equals("Chofer")) {%>
                                        <option value="Empleado">Empleado</option>
                                        <option value="Chofer" selected="">Chofer</option>
                                        <option value="Vendedor">Vendedor</option>
                                        <%} else if (empleado.getRol().equals("Vendedor")) {%>
                                        <option value="Empleado">Empleado</option>
                                        <option value="Chofer">Chofer</option>
                                        <option value="Vendedor" selected="">Vendedor</option>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Estaus</label>
                                    <select class="form-control" name="estatus" required="">
                                        <%if (empleado.getRol().equals("Administrador")) { %>
                                        <option value="Activo" selected="">Activo</option>
                                        <%} else if (empleado.getEstatus().equals("Activo")) { %>
                                        <option value="Activo" selected="">Activo</option>
                                        <option value="Inactivo">Inactivo</option>
                                        <%} else {%>
                                        <option value="Activo">Activo</option>
                                        <option value="Inactivo" selected="">Inactivo</option>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Jefe:</label>
                                        <input class="form-control" name="empleado" value="<%=empleado.getJefe()%>" readonly="">
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
