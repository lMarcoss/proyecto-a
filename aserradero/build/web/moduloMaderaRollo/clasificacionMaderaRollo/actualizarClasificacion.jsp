<%--
    Document   : actualizarCostoMaderaCompra
    Created on : 26/09/2016, 11:15:00 PM
    Author     : rcortes
--%>

<%@page import="entidades.maderaRollo.ClasificacionMaderaRollo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ClasificacionMaderaRollo clasificacion = (ClasificacionMaderaRollo) request.getAttribute("clasificacion");
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
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Actualizar datos</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Actualiza los datos necesarios yu guarda los cambios</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/ClasificacionMaderaRolloController?action=actualizar" method="post" id="formregistro">
                                <div class="form-group">
                                    <label class="control-label" for="proveedor">Proveedor</label>
                                    <select class="form-control" name="id_proveedor">
                                        <option value="<%=clasificacion.getId_proveedor()%>"><%=clasificacion.getProveedor()%></option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="clasificacion">Clasificación</label>
                                    <select class="form-control" name="clasificacion">
                                        <option value="<%=clasificacion.getClasificacion()%>"><%=clasificacion.getClasificacion()%></option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="costo">Costo</label>
                                    <input type="number" class="form-control" name="costo" step=".01" min="0.01" max="999999.99"  value="<%=clasificacion.getCosto()%>"/>
                                </div>
                                <div class="form-group pull-right">
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
