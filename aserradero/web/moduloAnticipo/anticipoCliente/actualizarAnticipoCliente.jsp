<%--
    Document   : actualizarAnticipoCliente
    Created on : 26/09/2016, 07:46:55 PM
    Author     : Marcos
--%>

<%@page import="entidades.anticipo.AnticipoCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%AnticipoCliente anticipoCliente = (AnticipoCliente) request.getAttribute("anticipoCliente");%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <title>Nuevo anticipo cliente</title>
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
                            <form action="/aserradero/AnticipoClienteController?action=actualizar" method="post" id="formregistro">
                                <input type="hidden" name="id_anticipo_c" value="<%=anticipoCliente.getId_anticipo_c()%>" readonly=""/>
                                <div class="form-group">
                                    <label class="control-label">Fecha:</label>
                                    <input class="form-control" type="date" name="fecha" value="<%=anticipoCliente.getFecha()%>" required="" readonly=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">cliente:</label>
                                    <select class="form-control" name="id_cliente" id="id_cliente">
                                        <option selected="" value="<%=anticipoCliente.getId_cliente()%>"><%=anticipoCliente.getCliente()%></option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto:</label>
                                    <input class="form-control" type="number" name="monto_anticipo" value="<%=anticipoCliente.getMonto_anticipo()%>" step="0.01" min="0.00" max="999999.99" required=""/>
                                </div>
                                <div class="form-group pull-right">
                                    <a href="/aserradero/AnticipoClienteController?action=listar"><input type="button" class="btn btn-warning"  value="Cancelar"/></a>
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
