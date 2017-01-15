<%--
    Document   : mostrarDetalleVM
    Created on : 26/12/2016, 10:27:56 PM
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.venta.VentaMayoreo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<VentaMayoreo> detalles = (List<VentaMayoreo>) request.getAttribute("detalles");
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

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Detalle de ventas</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Detalle de ventas</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Madera</th>
                                        <th>Piezas</th>
                                        <th>Volumen</th>
                                        <th>Costo</th>
                                        <th>Tipo_madera</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (VentaMayoreo detalle : detalles) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + detalle.getId_madera() + "</td>"
                                                    + "<td>" + detalle.getNum_piezas() + "</td>"
                                                    + "<td>" + detalle.getVolumen() + "</td>"
                                                    + "<td>" + detalle.getMonto() + "</td>"
                                                    + "<td>" + detalle.getTipo_madera() + "</td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/VentaMayoreoController?action=modificar&id_venta=" + detalle.getId_venta() + "&id_madera=" + detalle.getId_madera() + "&tipo_madera=" + detalle.getTipo_madera() + "\">Modificar</a></td>"
                                                    + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/VentaMayoreoController?action=eliminar&id_venta=" + detalle.getId_venta() + "&id_madera=" + detalle.getId_madera() + "&tipo_madera=" + detalle.getTipo_madera() + "';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-info" value="Regresar" onClick=" window.location.href = '/aserradero/VentaMayoreoController?action=listar'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
