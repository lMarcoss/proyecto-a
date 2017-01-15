<%--
    Document   : cuentaPorCobrares
    Created on : 28/09/2016, 12:13:27 AM
    Author     : Marcos
--%>

<%@page import="entidades.anticipo.CuentaPorCobrar"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<CuentaPorCobrar> cuentaPorCobrares = (List<CuentaPorCobrar>) request.getAttribute("cuentaPorCobrares");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Cuentas por cobrar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Cuentas por cobrar de Clientes</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Listado de cuentas por cobrar de clientes</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-busc">
                                <select name="nombre_campo" class="input-busc" >
                                    <option value="cliente">Cliente</option>
                                    <option value="monto">Monto</option>
                                </select>
                                <input type="text" class="input-busc" name="dato" placeholder="Escriba su búsqueda">
                                <input type="submit" value="Buscar" class="btn btn-success" >
                            </div>
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Cliente</th>
                                        <th>Monto </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (CuentaPorCobrar cuentaPorCobrar : cuentaPorCobrares) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + cuentaPorCobrar.getId_persona().substring(0, 18) + "\">" + cuentaPorCobrar.getPersona() + "</a></td>"
                                                    + "<td>" + cuentaPorCobrar.getMonto() + "</td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
