<%--
    Document   : nuevoUsuario
    Created on : 11-sep-2016, 18:15:05
    Author     : lmarcoss
--%>


<%@page import="entidades.empleado.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Nuevo usuario</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta y elija una buena contraseña</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/UsuarioController?action=insertar" method="post" id="formregistro">
                                <div class="form-group">
                                    <label class="control-label">Empleado:</label>
                                    <select class="form-control" name="id_empleado" required="">
                                        <option></option>
                                        <%
                                            for (Empleado empleado : empleados) {
                                                out.print("<option value='" + empleado.getId_empleado() + "'>" + empleado.getRol() + ": " + empleado.getEmpleado() + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="nombre_usuario">Nombre de usuario:</label>
                                    <input class="form-control" type="text" name="nombre_usuario" pattern="[A-Za-z].{3,}[A-Za-z]" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="45" required="" />
                                </div>
                                <div class="form-group">
                                    <label for="password">Contraseña</label>
                                    <input class="form-control" type="password" name="contrasenia" maxlength="20" required=""/>
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/UsuarioController?action=listar"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
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
