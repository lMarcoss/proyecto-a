<%--
    Document   : nuevoAnticipoCliente
    Created on : 26/09/2016, 06:45:08 PM
    Author     : Marcos
--%>


<%@page import="entidades.registros.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <link rel="stylesheet" href="/aserradero/css/formulario.css">
        <!--<script type="text/javascript" src="/aserradero/js/fechaActual.js"></script>-->
        <title>Nuevo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Nuevo anticipo de cliente</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rellene los campos de manera correcta</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/aserradero/AnticipoClienteController?action=insertar" method="post" id="formregistro">
                                <div class="form-group">
                                    <label class="control-label">Fecha:</label>
                                    <input type="date" class="form-control"  name="fecha" id="fecha" value="" required=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">cliente:</label>
                                    <select name="id_cliente" class="form-control"  required="" title="Si no existe el cliente que busca, primero agreguelo en la lista de clientes">
                                        <option></option>
                                        <%
                                            for (Cliente cliente : clientes) {
                                                out.print("<option value='" + cliente.getId_cliente() + "'>" + cliente.getCliente() + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Monto:</label>
                                    <input name="monto_anticipo" class="form-control" type="number" min='0.01' max='999999.99' step=".01" required=""/>
                                </div>
                                <div class="form-group pull-right">
                                    <a href="/aserradero/AnticipoClienteController?action=listar"><input type="button" value="Cancelar" class="btn btn-warning" /></a>
                                    <input type="submit" value="Guardar" class="btn btn-success" />
                                </div>
                            </form>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
