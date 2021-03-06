<%--
    Document   : listarPagoCompra
    Created on : 19-nov-2016, 22:22:17
    Author     : lmarcoss
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="entidades.maderaRollo.PagoCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<PagoCompra> listaPagoCompra = (List<PagoCompra>) request.getAttribute("listaPagoCompra");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Pago compra</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>        
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Pagos de compras</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Listado de pagos de compras</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Fecha</th>
                                        <th>Proveedor</th>
                                        <th>Monto pago</th>
                                        <th>Cuenta por pagar</th>
                                        <th>Cuenta por cobrar</th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        DecimalFormat form=new DecimalFormat("###,###.##");
                                        int i = 0;
                                        for (PagoCompra pago : listaPagoCompra) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + pago.getFecha() + "</td>"
                                                    + "<td>" + pago.getProveedor() + "</td>"
                                                    + "<td>" + form.format(pago.getMonto_pago()) + "</td>"
                                                    + "<td>" + form.format(pago.getMonto_por_pagar()) + "</td>"
                                                    + "<td>" + form.format(pago.getMonto_por_cobrar()) + "</td>"
                                                    + "<td><a class='btn btn-primary' href=\"/aserradero/PagoCompraController?action=detalle&id_pago=" + pago.getId_pago() + "&id_proveedor=" + pago.getId_proveedor() + "\">Detalle</a></td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/PagoCompraController?action=modificar&id_pago=" + pago.getId_pago() + "\">Modificar</a></td>"
                                                    + "<td><a class='btn btn-success' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PagoCompraController?action=eliminar&id_pago=" + pago.getId_pago() + "';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar_element">
                                <input type="button" class="btn btn-primary" value="Registrar pago" onClick=" window.location.href = '/aserradero/PagoCompraController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
