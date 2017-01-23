<%--
    Document   : actualizarAnticipoProveedor
    Created on : 27/09/2016, 03:01:06 PM
    Author     : Marcos
--%>

<%@page import="entidades.anticipo.AnticipoProveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    AnticipoProveedor anticipoProveedor = (AnticipoProveedor) request.getAttribute("anticipoProveedor");
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
                            <form action="/aserradero/AnticipoProveedorController?action=actualizar" method="post" id="formregistro">
                                <input type="hidden" name="id_anticipo_p" value="<%=anticipoProveedor.getId_anticipo_p()%>" readonly=""/>
                                <div class="form-group">
                                    <label class="control-label">Fecha:</label>
                                    <input type="date" name="fecha" class="form-control" value="<%=anticipoProveedor.getFecha()%>" required="" readonly=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">proveedor:</label>
                                    <select class="form-control" name="id_proveedor" id="id_proveedor" required="">
                                        <option selected="" value="<%=anticipoProveedor.getId_proveedor()%>"><%=anticipoProveedor.getProveedor()%></option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label class="control-label">Monto:</label>
                                    <input name="monto_anticipo" type="number" class="form-control" value="<%=anticipoProveedor.getMonto_anticipo()%>" step="any" required=""/>
                                </div>
                                <div class="form-group pull-right">
                                    <a href="/aserradero/AnticipoProveedorController?action=listar"><input type="button" value="Cancelar" class="btn btn-warning" /></a>
                                    <input type="submit" class="btn btn-success"  value="Guardar"/>
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
