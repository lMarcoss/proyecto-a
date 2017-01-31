<%-- 
    Document   : resumenHoy
    Created on : 31/01/2017, 06:48:10 AM
    Author     : lmarcoss
--%>
<%@page import="java.util.List"%>
<%@page import="entidades.venta.ReporteVentaPM"%>
<%
    List<ReporteVentaPM> listaReporte = (List<ReporteVentaPM>) request.getAttribute("listaReporte");
    ReporteVentaPM totalReporte = (ReporteVentaPM) request.getAttribute("totalReporte");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Producción madera</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Resumen de clasificación de madera vendida hoy</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Listado total de madera vendida</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Madera</th>
                                        <th>Num. piezas</th>
                                        <th>Volumen</th>
                                        <th>Monto</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (ReporteVentaPM reporte : listaReporte) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + reporte.getId_madera() + "</td>"
                                                    + "<td>" + reporte.getNum_piezas() + "</td>"
                                                    + "<td>" + reporte.getVolumen() + "</td>"
                                                    + "<td>" + reporte.getMonto() + "</td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                        if (totalReporte != null) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td><b>Total</b></td>"
                                                    + "<td><b>" + totalReporte.getNum_piezas() + "<b></td>"
                                                    + "<td><b>" + totalReporte.getVolumen() + "<b></td>"
                                                    + "<td><b>" + totalReporte.getMonto() + "<b></td>"
                                                    + "<td></td>"
                                                    + "</tr>");
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
