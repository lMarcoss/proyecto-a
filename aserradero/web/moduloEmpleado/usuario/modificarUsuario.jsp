<%--
    Document   : actualizarUsuario
    Created on : 15-sep-2016, 20:21:28
    Author     : lmarcoss
--%>

<%@page import="entidades.empleado.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession sesion = request.getSession(false);
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Actualizar</title>
    </head>
    <body>
        <!--menu-->
        <%if(((String)sesion.getAttribute("rol")).equals("Administrador")){%>
            <%@ include file="/TEMPLATE/menu_admin.jsp" %>
        <%}else{%>
            <%@ include file="/TEMPLATE/menu.jsp" %>
        <%}%>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Cambio de contraseña</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Establesca una contraseña segura, use números, letras mayúsculas y minúsculas y caracteres especiales</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/UsuarioController?action=actualizar" method="post" id="formregistro">
                                <div class="form-group">
                                    <label for="id_empleado">Empleado:</label>
                                    <select class="form-control" name="id_empleado" required="">
                                        <option value="<%= usuario.getId_empleado()%>"><%= usuario.getEmpleado()%></option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="nombre_usuario">Nombre de usuario:</label>
                                    <input class="form-control" type="text" name="nombre_usuario" value="<%=usuario.getNombre_usuario()%>" pattern="[A-Za-z].{3,}[A-Za-z]" title="Sólo letras aA-zZ, al menos 4 letras" maxlength="45" required="" readonly=""/>
                                </div>
                                <div class="form-group">
                                    <label for="password">Contraseña</label>
                                    <input class="form-control" type="password" name="contrasenia"  maxlength="20" required=""  placeholder="Escriba una nueva contraseña" title="Click en cancelar para no cambiar contraseña" />
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Email:</label>
                                    <input class="form-control" type="email" name="email" value="<%=usuario.getEmail()%>" maxlength="50"/>
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
