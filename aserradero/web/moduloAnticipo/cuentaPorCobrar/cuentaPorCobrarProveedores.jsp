<%--
    Document   : cuentaPorCobrarProveedores
    Created on : 27/12/2016, 06:07:36 PM
    Author     : lmarcoss
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
                    <h2>Cuentas por cobrar a proveedores</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Listado de cuentas por cobrar a proveedores</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Proveedor</th>
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
