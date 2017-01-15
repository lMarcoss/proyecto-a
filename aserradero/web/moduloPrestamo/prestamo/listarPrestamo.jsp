<%--
    Document   : prestamos
    Created on : 06-nov-2016, 0:57:20
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.prestamo.Prestamo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Prestamo> listaPrestamo = (List<Prestamo>) request.getAttribute("listaPrestamo");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Préstamos</title>
        <script>
            $(document).ready(function ($) {
                $("#registros").css("background", "#448D00");
                $("#personas").css("background", "#448D00");
            });
        </script>

    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Lista de préstamos</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Lista de todos los préstamos realizados</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-group form-busc">
                                <form method="POST" action="/aserradero/PrestamoController?action=buscar">
                                    <select class="input-busc" name="nombre_campo">
                                        <option value="fecha">Fecha</option>
                                        <option value="persona">Persona</option>
                                        <option value="monto">Monto</option>
                                        <option value="interes">% de interés</option>
                                    </select>
                                    <input type="text" class="input-busc" name="dato" placeholder="Escriba su búsqueda">
                                    <input type="submit" class="btn btn-success" value="Buscar">
                                </form>
                            </div>
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                <th>N°</th>
                                <th>Fecha</th>
                                <th>Prestador</th>
                                <th>Monto préstamo</th>
                                <th>Monto pagado</th>
                                <th>Monto por pagar</th>
                                <th>% de interés</th>
                                <th>Interés mensual</th>
                                <th></th>
                                <th></th>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (Prestamo prestamo : listaPrestamo) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + prestamo.getFecha() + "</td>"
                                                    + "<td>" + prestamo.getPrestador() + "</td>"
                                                    + "<td>" + prestamo.getMonto_prestamo() + "</td>"
                                                    + "<td>" + prestamo.getMonto_pagado() + "</td>"
                                                    + "<td>" + prestamo.getMonto_por_pagar() + "</td>"
                                                    + "<td>" + prestamo.getInteres() + "%" + "</td>"
                                                    + "<td>" + prestamo.getInteres_mesual() + "</td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/PrestamoController?action=modificar&id_prestamo=" + prestamo.getId_prestamo() + "\">Modificar</a></td>"
                                                    + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PrestamoController?action=eliminar&id_prestamo=" + prestamo.getId_prestamo() + "';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar_element">
                                <input class=" btn btn-primary" type="button" value="Registrar préstamo" onClick=" window.location.href = '/aserradero/PrestamoController?action=nuevo'">
                            </div>
                        </div><!-- Resultado Consulta-->
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
