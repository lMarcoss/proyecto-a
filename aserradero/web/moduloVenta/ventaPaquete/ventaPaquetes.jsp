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
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Ventas por paquete</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Listado de ventas por paquete</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
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
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (Venta venta : ventaPaquetes) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + venta.getFecha() + "</td>"
                                                    + "<td>" + venta.getId_venta() + "</td>"
                                                    + "<td>" + venta.getCliente() + "</td>"
                                                    + "<td>" + venta.getMonto() + "</td>"
                                                    + "<td>" + venta.getPago() + "</td>"
                                                    + "<td>" + venta.getEmpleado() + "</td>"
                                                    + "<td><a class='btn btn-primary' href=\"/aserradero/VentaPaqueteController?action=detalle&id_venta=" + venta.getId_venta() + "\">Detalle venta</a></td>"
                                                    + "<td><a class='btn btn-default' target=\"blank\" href=\"/aserradero/VentaController?action=ticket_costo&id_venta=" + venta.getId_venta() + "\">Ticket con costo</a></td>"
                                                    + "<td><a class='btn btn-info' target=\"blank\" href=\"/aserradero/VentaController?action=ticket_sin_costo&id_venta=" + venta.getId_venta() + "\">Ticket sin costo</a></td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/VentaController?action=modificar&id_venta=" + venta.getId_venta() + "&tipo_venta=Paquete\">Modificar pago</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-primary" value="Agregar venta paquete" onClick=" window.location.href = '/aserradero/VentaPaqueteController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
        <!-- ************************** opción de búsqueda-->
    </div> <!-- Fin opción de búsqueda-->
</body>
</html>
