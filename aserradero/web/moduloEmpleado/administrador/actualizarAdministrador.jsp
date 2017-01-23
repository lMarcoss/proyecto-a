<%--
    Document   : actualizarAdministrador
    Created on : 28-nov-2016, 15:55:29
    Author     : lmarcoss
--%>

<%@page import="entidades.empleado.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(false);
    Administrador administrador = (Administrador) request.getAttribute("administrador");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Modificar administrador</title>
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
                    <h2>Actualización de datos</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Actualice los datos necesarios y guarde los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/AdministradorController?action=actualizar" method="post" id="formregistro">
                                <div class="form-group">
                                    <label class="control-label">Administrador:</label>
                                    <select class="form-control" name="id_administrador" id="id_administrador" required="">
                                        <option value="<%=administrador.getId_administrador()%>" selected=""><%=administrador.getNombre()%></option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Cuenta incial</label>
                                    <input class="form-control" name="cuenta_inicial" type="number" value="<%=administrador.getCuenta_inicial()%>" min='0.00' max='99999999.99' step=".01"/>
                                </div>
                                <div class="form-group">
                                    <a href="/aserradero/AdministradorController?action=listar"><input type="button" class="btn btn-warning" value="Cancelar"/></a>
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
