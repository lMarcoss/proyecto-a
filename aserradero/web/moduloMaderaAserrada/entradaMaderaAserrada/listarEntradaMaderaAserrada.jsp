<%--
    Document   : produccionMaderas
    Created on : 28-sep-2016, 9:48:27
    Author     : lmarcoss
--%>
<%@page import="entidades.maderaAserrada.EntradaMaderaAserrada"%>
<%@page import="java.util.List"%>
<%
    List<EntradaMaderaAserrada> listaMEAserrada = (List<EntradaMaderaAserrada>) request.getAttribute("listaEntradaMaderaAserrada");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/TEMPLATE/head.jsp" %>
        <title>Producción madera</title>
    </head>
    <body>
        <!--menu-->
        <%@ include file="/TEMPLATE/menu.jsp" %>
        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Producción de madera</h2>
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
                                        <th>Fecha</th>
                                        <th>Id_madera</th>
                                        <th>Núm. piezas</th>
                                        <th>Registró</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int i = 0;
                                        for (EntradaMaderaAserrada maderaAserrada : listaMEAserrada) {
                                            out.print("<tr>"
                                                    + "<td>" + (i + 1) + "</td>"
                                                    + "<td>" + maderaAserrada.getFecha() + "</td>"
                                                    + "<td>" + maderaAserrada.getId_madera() + "</td>"
                                                    + "<td>" + maderaAserrada.getNum_piezas() + "</td>"
                                                    + "<td>" + maderaAserrada.getEmpleado() + "</td>"
                                                    + "<td><a class='btn btn-warning' href=\"/aserradero/EntradaMaderaAserradaController?action=modificar&id_entrada=" + maderaAserrada.getId_entrada() + "\">Modificar</a></td>"
                                                    + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/EntradaMaderaAserradaController?action=eliminar&id_entrada=" + maderaAserrada.getId_entrada() + "';};\">Eliminar</a></td>"
                                                    + "</tr>");
                                            i++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <div class="agregar_element">
                                <input type="button" class="btn btn-primary" value="Agregar entrada" onClick=" window.location.href = '/aserradero/EntradaMaderaAserradaController?action=nuevo'">
                            </div>
                        </div><!--panel body-->
                    </div><!--panel-->
                </div><!--col-md-12-->
            </div><!--row-->
        </div><!--<div class="container">-->
    </body>
</html>
