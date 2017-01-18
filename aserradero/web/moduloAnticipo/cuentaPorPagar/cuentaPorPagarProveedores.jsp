<%--
    Document   : cuentaPorPagarProveedores
    Created on : 27/12/2016, 05:51:09 PM
    Author     : lmarcoss
--%>

<%@page import="entidades.anticipo.CuentaPorPagar"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<CuentaPorPagar> cuentaPorPagares = (List<CuentaPorPagar>) request.getAttribute("cuentaPorPagares");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Cuentas por pagar</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Cuentas por pagar de proveedores</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Listado de cuentas por pagar a proveedores</h3>
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
                                        for (CuentaPorPagar cuentaPorPagar : cuentaPorPagares) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td><a href=\"/aserradero/PersonaController?action=buscar_persona&id_persona=" + cuentaPorPagar.getId_persona() + "\">" + cuentaPorPagar.getPersona() + "</a></td>"
                                                    + "<td>" + cuentaPorPagar.getMonto() + "</td>"
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
