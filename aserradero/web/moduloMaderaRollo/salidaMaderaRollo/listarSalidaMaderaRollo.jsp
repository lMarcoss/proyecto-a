<%--
    Document   : salidaMaderaRollos
    Created on : 27-oct-2016, 22:46:40
    Author     : lmarcoss
--%>

<%@page import="entidades.maderaRollo.SalidaMaderaRollo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<SalidaMaderaRollo> salidas = (List<SalidaMaderaRollo>) request.getAttribute("listaSalidaMaderaRollo");
    String mensaje = (String) request.getAttribute("mensaje");
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

        <input type="hidden" name="mensaje" id="mensaje" value="<%=mensaje%>">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="page-header">Actualización de datos</h2>
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
                                    <th></th>
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
                                                + "<td><a class='btn btn-warning' href=\"/aserradero/SalidaMaderaRolloController?action=modificar&id_salida=" + salida.getId_salida() + "\">Modificar</a></td>"
                                                + "</tr>");
                                        i++;
                                    }
                                %>
                            </tbody>
                        </table>
                        <div class="agregar_element">
                            <input type="button" class="btn btn-primary" value="Registrar salida" onClick=" window.location.href = '/aserradero/SalidaMaderaRolloController?action=nuevo'">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
