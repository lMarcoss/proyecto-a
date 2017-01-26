<%--
    Document   : listarPagoPrestamo
    Created on : 27-nov-2016, 22:22:17
    Author     : lmarcoss
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="entidades.prestamo.PagoPrestamo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<PagoPrestamo> listaPagoPrestamo = (List<PagoPrestamo>) request.getAttribute("listaPagoPrestamo");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Pago préstamo</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Pago de préstamos</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Registro de todos los pagos de préstamos</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Fecha</th>
                                        <th>Prestador</th>
                                        <th>Monto pagado</th>
                                        <th>Registró</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        DecimalFormat form=new DecimalFormat("###,###.##");
                                        int i = 0;
                                        for (PagoPrestamo pago : listaPagoPrestamo) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + pago.getFecha() + "</td>"
                                                    + "<td>" + pago.getPrestador() + "</td>"
                                                    + "<td>" + form.format(pago.getMonto_pago()) + "</td>"
                                                    + "<td>" + pago.getEmpleado() + "</td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/PagoPrestamoController?action=modificar&id_pago=" + pago.getId_pago() + "&id_prestamo=" + pago.getId_prestamo() + "\">Modificar</a></td>"
                                                    + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PagoPrestamoController?action=eliminar&id_pago=" + pago.getId_pago() + "';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar-element">
                                <input type="button" class="btn btn-primary" value="Registrar pago" onClick=" window.location.href = '/aserradero/PagoPrestamoController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->s
    </body>
</html>
