<%-- 
    Document   : resumenHoy
    Created on : 22/01/2017, 01:08:19 AM
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaAserrada.EntradaMaderaAserrada"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<EntradaMaderaAserrada> listaMEAserrada = (List<EntradaMaderaAserrada>) request.getAttribute("listaEntradaMaderaAserrada");
    EntradaMaderaAserrada entradaTotal = (EntradaMaderaAserrada) request.getAttribute("entradaTotal");
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
                    <h2>Total de entrada de madera aserrada hoy</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Listado de las maderas producidas</h3>
                        </div>
                        <div class="panel-body">
                            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th>Id_madera</th>
                                        <th>Núm. piezas</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (EntradaMaderaAserrada maderaAserrada : listaMEAserrada) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + maderaAserrada.getId_madera() + "</td>"
                                                    + "<td>" + maderaAserrada.getNum_piezas() + "</td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                        out.print("<tr>"
                                                + "<td>" + (i + 1) + "</td>"
                                                + "<td><b>Total<b></td>"
                                                + "<td><b>" + entradaTotal.getNum_piezas() + "<b></td>"
                                                + "</tr>");
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
