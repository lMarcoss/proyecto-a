<%-- 
    Document   : ventas
    Created on : 21-sep-2016, 21:11:30
    Author     : lmarcoss
--%>

<%@page import="entidades.venta.Venta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Venta> ventas = (List<Venta>) request.getAttribute("listaVentas");
//    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Ventas</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <div>
            <table class="table-condensed">
                <tr>
                    <th>N°</th>
                    <th>Fecha</th>
                    <th>Id venta</th>
                    <th>Cliente</th>
                    <th>Tipo venta</th>
                    <th>Pago</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    int i = 0;
                    for (Venta venta : ventas) {
                        out.print("<tr>"
                                + "<td>" + (i + 1) + "</td>"
                                + "<td>" + venta.getFecha() + "</td>"
                                + "<td>" + venta.getId_venta() + "</td>"
                                + "<td>" + venta.getCliente() + "</td>"
                                + "<td>" + venta.getTipo_venta() + "</td>"
                                + "<td>" + venta.getPago() + "</td>"
                                + "<td><a href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/VentaController?action=eliminar_venta&id_venta=" + venta.getId_venta() + "';};\">Eliminar</a></td>"
                                + "</tr>");
                        i++;
                    }
                %>
            </table>
        </div><!-- Resultado Consulta-->
    </body>
</html>
