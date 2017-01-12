<%--
    Document   : nuevoCostoMaderaCompra
    Created on : 26/09/2016, 11:14:36 PM
    Author     : rcortes
--%>

<%@page import="entidades.registros.Proveedor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
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
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Registrar clasificación y costo para un proveedor</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/ClasificacionMaderaRolloController?action=insertar" method="POST">
                                <div class="form-group">
                                    <label class="control-label" for="proveedor">Proveedor</label>
                                    <select class="form-control" name="id_proveedor">
                                        <option></option>
                                        <%
                                            for (Proveedor proveedor : proveedores) {
                                                out.print("<option value='"+proveedor.getId_proveedor()+"'>"+proveedor.getProveedor()+"</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="clasificacion">Clasificación</label>
                                    <select class="form-control" name="clasificacion">
                                        <option value="Primario">Primario</option>
                                        <option value="Secundario">Secundario</option>
                                        <option value="Terciario">Terciario</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="costo">Costo</label>
                                    <input type="number" class="form-control" step=".01" name="costo" min="0.01" max="999.99" required=""/>
                                </div>
                                <div class="control-group pull-right">
                                    <a href="/aserradero/ClasificacionMaderaRolloController?action=listar"><input class="btn btn-warning" type="button" value="Cancelar"/></a>
                                    <input type="submit" class="btn btn-success" value="Guardar"/>
                                </div>
                            </form>
                        </div><!--panel-body-->
                    </div><!--panel full-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--container-->
    </body>
</html>
