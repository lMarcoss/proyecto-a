<%-- 
    Document   : resumenHoy
    Created on : 30/01/2017, 02:42:57 PM
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaRollo.SalidaMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<SalidaMaderaRollo> salidas = (List<SalidaMaderaRollo>) request.getAttribute("listaSalida");
    SalidaMaderaRollo salidaTotal = (SalidaMaderaRollo) request.getAttribute("salidaTotal");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Salidas</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="page-header">Resumen de salida madera en rollo hoy</h2>
                </div>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Si el registro que busca no aparece, agréguelo</h3>
                    </div>
                    <div class="panel-body">
                        <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>N°</th>
                                    <th>Fecha</th>
                                    <th>Piezas primario</th>
                                    <th>Vol. primario</th>
                                    <th>Piezas secundario</th>
                                    <th>Vol. secundario</th>
                                    <th>Piezas terciario</th>
                                    <th>Vol. terciario</th>
                                    <th>Total piezas</th>
                                    <th>Volumen total</th>
                                    <th>Registró</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int i = 0;
                                    for (SalidaMaderaRollo salida : salidas) {
                                        out.print("<tr>"
                                                + "<td>" + (i + 1) + "</td>"
                                                + "<td>" + salida.getFecha() + "</td>"
                                                + "<td>" + salida.getNum_pieza_primario() + "</td>"
                                                + "<td>" + salida.getVolumen_primario() + "</td>"
                                                + "<td>" + salida.getNum_pieza_secundario() + "</td>"
                                                + "<td>" + salida.getVolumen_secundario() + "</td>"
                                                + "<td>" + salida.getNum_pieza_terciario() + "</td>"
                                                + "<td>" + salida.getVolumen_terciario() + "</td>"
                                                + "<td>" + salida.getNum_pieza_total() + "</td>"
                                                + "<td>" + salida.getVolumen_total() + "</td>"
                                                + "<td>" + salida.getEmpleado() + "</td>"
                                                + "</tr>");
                                        i++;
                                    }
                                    if (salidaTotal != null) {
                                        out.print("<tr>"
                                                + "<td>" + (i + 1) + "</td>"
                                                + "<td><b>Total</b></td>"
                                                + "<td><b>" + salidaTotal.getNum_pieza_primario() + "<b></td>"
                                                + "<td><b>" + salidaTotal.getVolumen_primario() + "<b></td>"
                                                + "<td><b>" + salidaTotal.getNum_pieza_secundario() + "<b></td>"
                                                + "<td><b>" + salidaTotal.getVolumen_secundario() + "<b></td>"
                                                + "<td><b>" + salidaTotal.getNum_pieza_terciario() + "<b></td>"
                                                + "<td><b>" + salidaTotal.getVolumen_terciario() + "<b></td>"
                                                + "<td><b>" + salidaTotal.getNum_pieza_total() + "<b></td>"
                                                + "<td><b>" + salidaTotal.getVolumen_total() + "<b></td>"
                                                + "<td></td>"
                                                + "</tr>");
                                    }

                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
