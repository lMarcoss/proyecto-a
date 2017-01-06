<%--
    Document   : maderaentradas
    Created on : 26/09/2016, 11:48:27 PM
    Author     : rcortes
--%>

<%@page import="java.math.MathContext"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.maderaRollo.InventarioMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<InventarioMaderaRollo> listaInventario = (List<InventarioMaderaRollo>) request.getAttribute("listaInventario");
    String mensaje = (String) request.getAttribute("mensaje");
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

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">

        <% if (!listaInventario.isEmpty()) {%>
        <!-- ************************* Resultado Consulta-->
        <h1>Inventario madera en rollo</h1>
        <div>
            <table class="table-condensed">
                <tr>
                    <th>Clasificación</th>
                    <th>piezas</th>
                    <th>Volúmen</th>
                    <th>Costo por volumen</th>
                    <th>Costo total</th>
                </tr>
                <%
                    for (InventarioMaderaRollo inventario : listaInventario) {
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
            </table>            
        </div><!-- Resultado Consulta-->
        <%} else {%>
        <h3 style="color: red;">No hay inventario</h3>
        <%}%>
    </body>
</html>
