<%--
    Document   : ventaExtras
    Created on : 21-sep-2016, 23:43:15
    Author     : lmarcoss
--%>

<%@page import="entidades.venta.Venta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Venta> ventaExtras = (List<Venta>) request.getAttribute("ventaExtras");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Ventas </title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>" >
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Venta Extra</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Listado de ventas extras realizadas</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-busc">
                                <form method="POST" action="/aserradero/VentaExtraController?action=buscar">
                                    <select name="nombre_campo" class="input-busc" >
                                        <option value="fecha">Fecha</option>
                                        <option value="id_venta">Id_venta</option>
                                        <option value="cliente">Cliente</option>
                                        <option value="monto">Monto</option>
                                        <option value="pago">Pago en efectivo</option>
                                        <option value="observacion">Observacion</option>
                                        <option value="empleado">Registró</option>
                                    </select>
                                    <input type="search" name="dato" placeholder="Escriba su búsqueda" class="input-busc">
                                    <input type="submit" value="Buscar" class="btn btn-success">
                                </form>
                            </div>
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
                                        for (Venta venta : ventaExtras) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + venta.getFecha() + "</td>"
                                                    + "<td>" + venta.getId_venta()+ "</td>"
                                                    + "<td>" + venta.getCliente() + "</td>"
                                                    + "<td>" + venta.getMonto() + "</td>"
                                                    + "<td>" + venta.getPago()+ "</td>"
                                                    + "<td>" + venta.getEmpleado() + "</td>"
                                                    + "<td><a class='btn btn-primary' href=\"/aserradero/VentaExtraController?action=detalle&id_venta=" + venta.getId_venta() + "\">Detalle venta</a></td>"
                                                    + "<td><a class='btn btn-default' target=\"blank\" href=\"/aserradero/VentaController?action=ticket_costo&id_venta=" + venta.getId_venta() + "\">Ticket con costo</a></td>"
                                                    + "<td><a class='btn btn-info' target=\"blank\" href=\"/aserradero/VentaController?action=ticket_sin_costo&id_venta=" + venta.getId_venta() + "\">Ticket sin costo</a></td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/VentaController?action=modificar&id_venta=" + venta.getId_venta() + "&tipo_venta=Extra\">Modificar pago</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-primary" value="Agregar venta extra" onClick=" window.location.href = '/aserradero/VentaExtraController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
