<%-- 
    Document   : resumenHoy
    Created on : 22/01/2017, 01:53:45 AM
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaRollo.InventarioMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<InventarioMaderaRollo> listaEntrada = (List<InventarioMaderaRollo>) request.getAttribute("listaEntrada");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Maderas en rollo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <% if (!listaEntrada.isEmpty()) {%>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Resumen de entrada madera en rollo hoy</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Para agregar inventario de click en (Madera en rollo -> Entrada)</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>Clasificación</th>
                                        <th>piezas</th>
                                        <th>Volúmen</th>
                                        <th>Costo por volumen</th>
                                        <th>Costo total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (InventarioMaderaRollo inventario : listaEntrada) {
                                            out.print("<tr>"
                                                    + "<td>Primario</td>"
                                                    + "<td>" + inventario.getNum_pieza_primario() + "</td>"
                                                    + "<td>" + inventario.getVolumen_primario() + "</td>"
                                                    + "<td>" + inventario.getCosto_primario() + "</td>"
                                                    + "<td><b>" + inventario.getCosto_total_primario() + "</b></td>"
                                                    + "</tr><tr>"
                                                    + "<td>Secundario</td>"
                                                    + "<td>" + inventario.getNum_pieza_secundario() + "</td>"
                                                    + "<td>" + inventario.getVolumen_secundario() + "</td>"
                                                    + "<td>" + inventario.getCosto_secundario() + "</td>"
                                                    + "<td><b>" + inventario.getCosto_total_secundario() + "</b></td>"
                                                    + "</tr><tr>"
                                                    + "<td>Terciario</td>"
                                                    + "<td>" + inventario.getNum_pieza_terciario() + "</td>"
                                                    + "<td>" + inventario.getVolumen_terciario() + "</td>"
                                                    + "<td>" + inventario.getCosto_terciario() + "</td>"
                                                    + "<td><b>" + inventario.getCosto_total_terciario() + "</b></td>"
                                                    + "</tr><tr>"
                                                    + "<td>Total</td>"
                                                    + "<td><b>" + inventario.getNum_pieza_total() + "</b></td>"
                                                    + "<td><b>" + inventario.getVolumen_total() + "</b></td>"
                                                    + "<td></td>"
                                                    + "<td><b>" + inventario.getCosto_total() + "</b></td>"
                                                    + "</tr>");
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>    
        <%} else {%>
        <h3 style="color: red;">No hay inventario</h3>
        <%}%>
    </body>
</html>
