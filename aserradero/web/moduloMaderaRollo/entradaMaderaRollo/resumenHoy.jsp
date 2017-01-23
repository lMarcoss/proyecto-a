<%-- 
    Document   : resumenHoy
    Created on : Jan 22, 2017, 12:46:20 PM
    Author     : marcos
--%>

<%@page import="entidades.maderaRollo.EntradaMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<EntradaMaderaRollo> listaEntrada = (List<EntradaMaderaRollo>) request.getAttribute("listaEntrada");
    EntradaMaderaRollo entradaTotal = (EntradaMaderaRollo) request.getAttribute("entradaTotal");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entradas hoy</title>
        <%@ include file="/TEMPLATE/head.jsp" %>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Resumen de entrada madera en rollo hoy</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Para agregar inventario de click en (Madera en rollo -> Entrada)</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>Clasificación</th>
                                        <th>piezas</th>
                                        <th>Volúmen</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (EntradaMaderaRollo inventario : listaEntrada) {
                                            out.print("<tr>"
                                                    + "<td>Primario</td>"
                                                    + "<td>" + inventario.getNum_pieza_primario() + "</td>"
                                                    + "<td>" + inventario.getVolumen_primario() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td>Secundario</td>"
                                                    + "<td>" + inventario.getNum_pieza_secundario() + "</td>"
                                                    + "<td>" + inventario.getVolumen_secundario() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td>Terciario</td>"
                                                    + "<td>" + inventario.getNum_pieza_terciario() + "</td>"
                                                    + "<td>" + inventario.getVolumen_terciario() + "</td>"
                                                    + "</tr><tr>"
                                                    + "<td>Total</td>"
                                                    + "<td><b>" + entradaTotal.getNum_pieza_total() + "</b></td>"
                                                    + "<td><b>" + entradaTotal.getVolumen_total() + "</b></td>"
                                                    + "</tr>");
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>  
    </body>
</html>
