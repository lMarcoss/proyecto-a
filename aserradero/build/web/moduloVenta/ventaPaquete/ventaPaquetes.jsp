<%-- 
    Document   : produccionDetalles
    Created on : 28-sep-2016, 9:55:51
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.venta.Venta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Venta> ventaPaquetes = (List<Venta>) request.getAttribute("listaVentas");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Ventas por paquete</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">

        <!-- ************************** opción de búsqueda-->
        <form method="POST" action="/aserradero/VentaPaqueteController?action=buscar">
            <table>
                <tr>
                    <td>
                        <select name="nombre_campo" >
                            <option value="fecha">Fecha</option>
                            <option value="id_venta">Id_venta</option>
                            <option value="cliente">Cliente</option>
                            <option value="monto">Monto total</option>
                            <option value="observacion">Observacion</option>
                            <option value="empleado">Registró</option>
                        </select>
                    </td>
                    <td><input type="search" name="dato" placeholder="Escriba su búsqueda"></td>
                    <td colspan="2"><input type="submit" value="Buscar"></td>
                </tr>
            </table>
        </form>
    </div> <!-- Fin opción de búsqueda-->

    <!-- ************************* Resultado Consulta-->
    <div>
        <table class="table-condensed">
            <tr>
                <th>N°</th>
                <th>Fecha</th>
                <th>Id_venta</th>
                <th>Cliente</th>
                <th>Monto total</th>
                <th>Pago en efectivo</th>
                <th>Registró</th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <%
                int i = 0;
                for (Venta venta : ventaPaquetes) {
                    out.print("<tr>"
                            + "<td>" + (i + 1) + "</td>"
                            + "<td>" + venta.getFecha() + "</td>"
                            + "<td>" + venta.getId_venta()+ "</td>"
                            + "<td>" + venta.getCliente() + "</td>"
                            + "<td>" + venta.getMonto() + "</td>"
                            + "<td>" + venta.getPago()+ "</td>"
                            + "<td>" + venta.getEmpleado() + "</td>"
                            + "<td><a href=\"/aserradero/VentaPaqueteController?action=detalle&id_venta=" + venta.getId_venta() + "\">Detalle venta</a></td>"
                            + "<td><a target=\"blank\" href=\"/aserradero/VentaController?action=ticket_costo&id_venta=" + venta.getId_venta() + "\">Ticket con costo</a></td>"
                            + "<td><a target=\"blank\" href=\"/aserradero/VentaController?action=ticket_sin_costo&id_venta=" + venta.getId_venta() + "\">Ticket sin costo</a></td>"
                            + "<td><a href=\"/aserradero/VentaController?action=modificar&id_venta=" + venta.getId_venta() + "&tipo_venta=Paquete\">Modificar pago</a></td>"
                            + "</tr>");
                    i++;
                }
            %>
        </table>
        <div>
            <input type="button" value="Agregar venta paquete" onClick=" window.location.href = '/aserradero/VentaPaqueteController?action=nuevo'">
        </div>
    </div><!-- Resultado Consulta-->
</body>
</html>
